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

public class DeleteOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(GeneralConstant.USER);
        String page;
        try {
            CustomerService customerService = new CustomerServiceImpl();
            CourierService courierService = new CourierServiceImpl();
            customerService.deleteOrder(Integer.parseInt(request.getParameter(GeneralConstant.DELETED_ORDER_ID)));
            session.removeAttribute(GeneralConstant.EXISTED_ORDERS);
            session.removeAttribute(GeneralConstant.COMPLETED_ORDERS);

            List<CustomerOrder> listCustomerOrder = courierService.getCustomerOrdersOfOneCustomer(user.getLogin());
            List<CustomerOrder> existedOrders = new ArrayList<>();
            List<CustomerOrder> completedOrders = new ArrayList<>();
            for(CustomerOrder order : listCustomerOrder){
                if(order.getStatus().getValue().equals(GeneralConstant.POSTED) || order.getStatus().getValue().equals(GeneralConstant.DELIVERED)){
                    existedOrders.add(order);
                }else {
                    completedOrders.add(order);
                }
            }
            session.setAttribute(GeneralConstant.EXISTED_ORDERS, existedOrders);
            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, completedOrders);
            page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
