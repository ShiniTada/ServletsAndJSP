package com.epam.couriers.command.impl.customer;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.CustomerService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import com.epam.couriers.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This command set customer's marks
 */
public class SetMarksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        HttpSession session = request.getSession();

        int quality = Integer.parseInt(request.getParameter(GeneralConstant.MARK_QUALITY));
        int politeness = Integer.parseInt(request.getParameter(GeneralConstant.MARK_POLITENESS));
        int punctuality = Integer.parseInt(request.getParameter(GeneralConstant.MARK_PUNCTUALITY));
        int orderId = 0;
        String courierLogin = request.getParameter(GeneralConstant.COURIER_LOGIN);
        User user = (User) session.getAttribute(GeneralConstant.USER);

        List<CustomerOrder> existed = (List<CustomerOrder>) session.getAttribute(GeneralConstant.EXISTED_ORDERS);
        for (CustomerOrder selectedOrder : existed) {
            if (selectedOrder.getCourier().getLogin().equals(courierLogin)) {
                orderId = selectedOrder.getId();
                break;
            }
        }
        if (orderId == 0) {
            throw new CommandException("not found orderId");
        }

        CustomerService customerService = new CustomerServiceImpl();
        CourierService courierService = new CourierServiceImpl();
        try {
            customerService.setMarks(courierLogin, quality, politeness, punctuality);
            customerService.setOrderCompleted(orderId);

            session.removeAttribute(GeneralConstant.EXISTED_ORDERS);
            session.removeAttribute(GeneralConstant.COMPLETED_ORDERS);
            List<CustomerOrder> orders = courierService.getCustomerOrdersOfOneCustomer(user.getLogin());
            List<CustomerOrder> existedOrders = new ArrayList<>();
            List<CustomerOrder> completedOrders = new ArrayList<>();

            for (CustomerOrder order : orders) {
                if (order.getStatus().getValue().equals(GeneralConstant.POSTED) || order.getStatus().getValue().equals(GeneralConstant.DELIVERED)) {
                    existedOrders.add(order);
                } else {
                    completedOrders.add(order);
                }
            }
            session.setAttribute(GeneralConstant.EXISTED_ORDERS, existedOrders);
            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, completedOrders);
            page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }

}
