package com.epam.couriers.command.impl.courier;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AcceptOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(GeneralConstant.USER);
        String page;
        try {
            CourierService courierService = new CourierServiceImpl();
            if(request.getParameter(GeneralConstant.ACCEPT) != null) {
                courierService.acceptOrder(Integer.parseInt(request.getParameter(GeneralConstant.ACCEPT)));
            } else {
                courierService.rejectOrder(Integer.parseInt(request.getParameter(GeneralConstant.REJECT)));
            }
            session.removeAttribute(GeneralConstant.EXISTED_ORDERS);
            session.removeAttribute(GeneralConstant.COMPLETED_ORDERS);

           List<CustomerOrder> listCustomerOrder = courierService.getCustomerOrdersOfOneCourier(user.getLogin());
           List<CustomerOrder> existedOrders = new ArrayList<>();
            List<CustomerOrder> completedOrders = new ArrayList<>();
            for(CustomerOrder order : listCustomerOrder){
                if(order.getStatus().getValue().equals("posted") || order.getStatus().getValue().equals("delivered")){
                    existedOrders.add(order);
                }else {
                    completedOrders.add(order);
                }
            }
            session.setAttribute(GeneralConstant.EXISTED_ORDERS, existedOrders);
            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, completedOrders);
            page = PathManager.getProperty(PathManager.COURIER_PAGE);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
