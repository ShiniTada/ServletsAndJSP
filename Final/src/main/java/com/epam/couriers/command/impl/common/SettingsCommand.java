package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SettingsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
            String page;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(GeneralConstant.USER);

            String currentPassword = request.getParameter(GeneralConstant.USER_CURRENT_PASSWORD);
            String newPassword = request.getParameter(GeneralConstant.USER_NEW_PASSWORD);
            String repeatedNewPassword = request.getParameter(GeneralConstant.USER_REPEATED_NEW_PASSWORD);
            String hashCurrentPassword = DigestUtils.md5Hex(currentPassword);

            if(!hashCurrentPassword.equals(user.getPassword())){
                request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.INCORRECT_PASSWORD);
                return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
            }
            if(!newPassword.equals(repeatedNewPassword)){
                request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.NOT_EQUALS_PASSWORDS);
                return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
            }

            try {
                UserService userService = new UserServiceImpl(DAOFactory.getUserDAO());
                userService.changePassword(user.getId(), newPassword);
                String hashNewPassword = DigestUtils.md5Hex(newPassword);
                user.setPassword(hashNewPassword);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            if(user.getRole().equals(RoleEnum.ADMIN)) {
                page = PathManager.getProperty(PathManager.ADMIN_PAGE);
            } else if (user.getRole().equals(RoleEnum.CUSTOMER)) {
                    page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
                    } else {
                        page = PathManager.getProperty(PathManager.COURIER_PAGE);
            }
            session.removeAttribute(GeneralConstant.USER);
            session.setAttribute(GeneralConstant.USER, user);
             session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        }
}
