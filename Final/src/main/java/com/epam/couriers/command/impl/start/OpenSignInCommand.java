package com.epam.couriers.command.impl.start;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenSignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<User> users = adminService.getAllUsers();
            request.setAttribute(GeneralConstant.LIST_USERS, users);
            page = PathManager.getProperty(PathManager.SIGN_IN_PAGE);
            request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
