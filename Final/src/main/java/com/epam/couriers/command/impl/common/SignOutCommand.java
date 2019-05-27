package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Sign out user from the site
 */
public class SignOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(GeneralConstant.USER);
        session.removeAttribute(GeneralConstant.LIST_TRANSPORT);
        session.removeAttribute(GeneralConstant.LIST_GOODS);
        session.removeAttribute(GeneralConstant.COURIER_RECORD);
        session.removeAttribute(GeneralConstant.CUSTOMER_ORDERS);
        String page = PathManager.getProperty(PathManager.HOME_PAGE);
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }
}
