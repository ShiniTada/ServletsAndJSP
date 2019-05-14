package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CourierSevrice;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import com.epam.couriers.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This command registers the courier and opens his page
 */
public class CourierRegistrationCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PathManager.getProperty(PathManager.ERROR_PAGE_404);
        HttpSession session = request.getSession();
        List<String> transport = new ArrayList<>();
        if(request.getParameter(GeneralConstant.CYCLE_ATTRIBUTE) != null){
            transport.add(GeneralConstant.CYCLE_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.MOTORCYCLE_ATTRIBUTE) != null){
            transport.add(GeneralConstant.MOTORCYCLE_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.CAR_ATTRIBUTE) != null){
            transport.add(GeneralConstant.CAR_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.TRUCK_ATTRIBUTE) != null){
            transport.add(GeneralConstant.TRUCK_ATTRIBUTE);
        }
        List<String> goods = new ArrayList<>();
        if(request.getParameter(GeneralConstant.FOOD_ATTRIBUTE) != null){
            goods.add(GeneralConstant.FOOD_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.TECH_ATTRIBUTE) != null){
            goods.add(GeneralConstant.TECH_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.FURNITURE_ATTRIBUTE) != null){
            goods.add(GeneralConstant.FURNITURE_ATTRIBUTE);
        }
        if(request.getParameter(GeneralConstant.EASY_TO_BEAT_ATTRIBUTE) != null){
            goods.add(GeneralConstant.EASY_TO_BEAT_ATTRIBUTE);
        }

        try {
            User user;
            UserService userService = new UserServiceImpl();
            user = userService.courierRegistration(request.getParameter(GeneralConstant.USER_LOGIN), request.getParameter(GeneralConstant.USER_PASSWORD));

            CourierRecord courierRecord;
            CourierSevrice courierSevrice = new CourierServiceImpl();
            courierRecord = courierSevrice.addCourierRecord(user.getId());

            courierSevrice.addAllTransports(courierRecord.getId(), transport);
            courierSevrice.addAllGoods(courierRecord.getId(), goods);
            session.setAttribute(GeneralConstant.USER, user);
            session.setAttribute(GeneralConstant.LIST_GOODS, goods);
            session.setAttribute(GeneralConstant.LIST_TRANSPORT, transport);
            session.setAttribute(GeneralConstant.COURIER_RECORD, courierRecord);
            page = PathManager.getProperty(PathManager.COURIER_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }
}
