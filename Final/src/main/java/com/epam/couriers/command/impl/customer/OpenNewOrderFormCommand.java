package com.epam.couriers.command.impl.customer;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * This command open form of new order
 */
public class OpenNewOrderFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int courierId = Integer.parseInt(request.getParameter(GeneralConstant.COURIER_ID));
        request.setAttribute(GeneralConstant.COURIER_ID, courierId);
        String page = PathManager.getProperty(PathManager.ORDER_FORM_PAGE);
        request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }
}
