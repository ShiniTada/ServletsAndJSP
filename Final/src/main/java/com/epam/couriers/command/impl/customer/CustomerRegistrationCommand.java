package com.epam.couriers.command.impl.customer;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This command registers the customer and opens his page
 */
public class CustomerRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        HttpSession session = request.getSession();
        String realLogin = request.getParameter(GeneralConstant.USER_LOGIN);
        List<User> allUsers = (List<User>) session.getAttribute(GeneralConstant.LIST_USERS);
        for (User u : allUsers) {
            if(u.getLogin().equalsIgnoreCase(realLogin)) {
                request.setAttribute(GeneralConstant.MESSAGE_ATRIBUTE, AllErrorMessages.LOGIN_BAD);
                return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
            }
        }

        String realPassword = request.getParameter(GeneralConstant.USER_PASSWORD);
        if(!realPassword.equals(request.getParameter(GeneralConstant.USER_REPEATED_PASSWORD))){
            request.setAttribute(GeneralConstant.MESSAGE_ATRIBUTE, AllErrorMessages.NOT_EQUALS_PASSWORDS);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(realLogin.equals("")) {
            request.setAttribute(GeneralConstant.MESSAGE_ATRIBUTE, AllErrorMessages.EMPTY_LOGIN);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(realPassword.equals("")) {
            request.setAttribute(GeneralConstant.MESSAGE_ATRIBUTE, AllErrorMessages.EMPTY_PASSWORD);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }

        try {
            UserService userService = new UserServiceImpl();
            User user = userService.registration(realLogin, request.getParameter(GeneralConstant.USER_PASSWORD), RoleEnum.CUSTOMER);
            session.setAttribute(GeneralConstant.USER, user);
            session.setAttribute(GeneralConstant.EXISTED_ORDERS, null);
            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, null);
            page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        session.removeAttribute(GeneralConstant.LIST_USERS);
        session.removeAttribute(GeneralConstant.MESSAGE_ATRIBUTE);
        return page;
    }
}
