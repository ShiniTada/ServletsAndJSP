package com.epam.couriers.command.impl.customer;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.CustomerService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import com.epam.couriers.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddFilledCustomerOrderCommand implements Command {



    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(GeneralConstant.USER);
        int customerId = user.getId();
        CourierRecord courierRecord = (CourierRecord) session.getAttribute(GeneralConstant.COURIER_RECORD);
        int courierId = courierRecord.getCourier().getId();

        String whatDeliver = request.getParameter(GeneralConstant.WHAT_DELIVER);
        if(whatDeliver.equals("")) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        String from = request.getParameter(GeneralConstant.FROM);
        if(from.equals("")) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        String to = request.getParameter(GeneralConstant.TO);
        if(to.equals("")) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.DELIVERY_DATE) == null) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        String date = request.getParameter(GeneralConstant.DELIVERY_DATE);
        if(date.equals("")) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.NUMBER_OF_GOODS) == null) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        String numberOfGoods =request.getParameter(GeneralConstant.NUMBER_OF_GOODS);
        if(request.getParameter(GeneralConstant.WEIGHT) == null) {
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        String weight = request.getParameter(GeneralConstant.WEIGHT);
        CustomerService customerService = new CustomerServiceImpl();
        CourierService courierService = new CourierServiceImpl();
        try {
            customerService.addCustomerOrder(customerId, courierId, whatDeliver, from, to, date, numberOfGoods, weight);
            session.removeAttribute(GeneralConstant.EXISTED_ORDERS);
            session.removeAttribute(GeneralConstant.COMPLETED_ORDERS);
            List<CustomerOrder> orders = courierService.getCustomerOrdersOfOneCustomer(user.getLogin());
            List<CustomerOrder> existedOrders = new ArrayList<>();
            List<CustomerOrder>  completedOrders = new ArrayList<>();

            for(CustomerOrder order : orders){
                if(order.getStatus().getValue().equals(GeneralConstant.POSTED) || order.getStatus().getValue().equals(GeneralConstant.DELIVERED)){
                    existedOrders.add(order);
                }else {
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
