package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This command shows information about all orders made
 */
public class GetAllOrdersCommand implements Command {

    private static final String LIST_ORDERS = "listOrders";

    private static final String PAGE_ATTRIBUTE = "page";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<CustomerOrder> orders = adminService.getCustomerOrders();
            request.getSession().setAttribute(LIST_ORDERS, orders);
            page = PathManager.getProperty(PathManager.TABLE_ORDERS_PAGE);
            request.getSession().setAttribute(PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
