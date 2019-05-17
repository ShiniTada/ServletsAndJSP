package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
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

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<CustomerOrder> orders = adminService.getCustomerOrders();
            request.getSession().setAttribute(GeneralConstant.LIST_ORDERS, orders);
            page = PathManager.getProperty(PathManager.TABLE_ORDERS_PAGE);
            request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
