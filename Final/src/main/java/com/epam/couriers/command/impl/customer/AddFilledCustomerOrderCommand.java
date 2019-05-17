package com.epam.couriers.command.impl.customer;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.impl.CourierServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
        String from = request.getParameter(GeneralConstant.FROM);
        String to = request.getParameter(GeneralConstant.TO);
        String date = request.getParameter(GeneralConstant.DELIVERY_DATE);
        String countPoints = request.getParameter(GeneralConstant.COUNT_POINTS);
        String weight = request.getParameter(GeneralConstant.WEIGHT);

        System.out.println("customer: " + customerId + " courier: " + courierId + "what: " + whatDeliver);
        System.out.println("from: " + from + "to: " + to);
        System.out.println("date: " + date + "countPoints: " + countPoints + "weight: " + weight);

        page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }
}
