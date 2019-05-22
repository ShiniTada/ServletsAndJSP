package com.epam.couriers.command.impl.courier;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
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
        String page;
        HttpSession session = request.getSession();
        String realLogin = request.getParameter(GeneralConstant.USER_LOGIN);
        List<User> allUsers = (List<User>) session.getAttribute(GeneralConstant.LIST_USERS);
        for (User u : allUsers) {
            if(u.getLogin().equalsIgnoreCase(realLogin)) {
               request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.LOGIN_BAD);
                return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
            }
        }

        String realPassword = request.getParameter(GeneralConstant.USER_PASSWORD);
        if(!realPassword.equals(request.getParameter(GeneralConstant.USER_REPEATED_PASSWORD))){
            request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.NOT_EQUALS_PASSWORDS);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(realLogin.equals("")) {
            request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.EMPTY_LOGIN);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        if(realPassword.equals("")) {
            request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.EMPTY_PASSWORD);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }

        List<String> transport = new ArrayList<>();
        if (request.getParameter(GeneralConstant.CYCLE_ATTRIBUTE) != null) {
            transport.add(GeneralConstant.CYCLE_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.MOTORCYCLE_ATTRIBUTE) != null) {
            transport.add(GeneralConstant.MOTORCYCLE_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.CAR_ATTRIBUTE) != null) {
            transport.add(GeneralConstant.CAR_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.TRUCK_ATTRIBUTE) != null) {
            transport.add(GeneralConstant.TRUCK_ATTRIBUTE);
        }
        if(transport.size() == 0) {
            request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.EMPTY_TRANSPORT);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }
        List<String> goods = new ArrayList<>();
        if (request.getParameter(GeneralConstant.FOOD_ATTRIBUTE) != null) {
            goods.add(GeneralConstant.FOOD_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.TECH_ATTRIBUTE) != null) {
            goods.add(GeneralConstant.TECH_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.FURNITURE_ATTRIBUTE) != null) {
            goods.add(GeneralConstant.FURNITURE_ATTRIBUTE);
        }
        if (request.getParameter(GeneralConstant.EASY_TO_BEAT_ATTRIBUTE) != null) {
            goods.add(GeneralConstant.EASY_TO_BEAT_ATTRIBUTE);
        }
        if(goods.size() == 0) {
            request.setAttribute(GeneralConstant.MESSAGE_ATTRIBUTE, AllErrorMessages.EMPTY_GOODS);
            return (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        }

        try {
            User user;
            UserService userService = new UserServiceImpl(DAOFactory.getUserDAO());
            user = userService.registration(realLogin, request.getParameter(GeneralConstant.USER_PASSWORD), RoleEnum.COURIER);

            CourierRecord courierRecord;
            CourierService courierService = new CourierServiceImpl();
            courierRecord = courierService.addCourierRecord(user.getId());

            courierService.addAllTransports(courierRecord.getId(), transport);
            courierService.addAllGoods(courierRecord.getId(), goods);
            session.setAttribute(GeneralConstant.USER, user);
            session.setAttribute(GeneralConstant.LIST_GOODS, goods);
            session.setAttribute(GeneralConstant.LIST_TRANSPORT, transport);
            session.setAttribute(GeneralConstant.COURIER_RECORD, courierRecord);
            page = PathManager.getProperty(PathManager.COURIER_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        session.removeAttribute(GeneralConstant.LIST_USERS);
        session.removeAttribute(GeneralConstant.MESSAGE_ATTRIBUTE);
        return page;
    }
}
