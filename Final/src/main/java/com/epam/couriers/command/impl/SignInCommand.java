package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.CourierSevrice;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.errormessage.Message;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import com.epam.couriers.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.MESSAGE;


/**
 * Sign in user on site
 */
public class SignInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);
    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
//    private static final int  LIVE_TIME = 60 * 10;
    private static final String PAGE_ATTRIBUTE = "page";



    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PathManager.getProperty(PathManager.ERROR_PAGE_404);
        HttpSession session = request.getSession();
        try {
            User user;
                UserService userService = new UserServiceImpl();
                user = userService.logIn(request.getParameter(LOGIN), request.getParameter(PASSWORD));
                if (user != null) {
                    session.setAttribute(USER, user);
//                    session.setMaxInactiveInterval(LIVE_TIME);
//                    request.setAttribute(USER, user);
                    LOGGER.debug("User \"" + user.getLogin() + "\" signed in");
                    switch (user.getRole().getValue()) {
                        case "admin":
                            page = PathManager.getProperty(PathManager.ADMIN_PAGE);
                            break;
                        case "courier":
                            CourierSevrice courierSevrice = new CourierServiceImpl();
                            CourierRecord courierRecord = courierSevrice.getCourierRecord(user.getId());
                            List<Transport> transport = courierSevrice.getTransportsOfOneCourier(courierRecord.getId());
                            List<String> listTransport = new ArrayList<>();
                            for (Transport t : transport){
                                listTransport.add(t.getTypeTransport().getValue());
                            }
                            List<Goods> goods = courierSevrice.getGoodsOfOneCourier(courierRecord.getId());
                            List<String> listGoods = new ArrayList<>();
                            for (Goods g : goods){
                                listGoods.add(g.getTypeGoods().getValue());
                            }
                            List<CustomerOrder> listCustomerOrder = courierSevrice.getCustomerOrdersOfOneCourier(user.getId());
                            session.setAttribute(GeneralConstant.COURIER_RECORD, courierRecord);
                            session.setAttribute(GeneralConstant.LIST_TRANSPORT, listTransport);
                            session.setAttribute(GeneralConstant.LIST_GOODS, listGoods);
                            session.setAttribute(GeneralConstant.CUSTOMER_ORDERS, listCustomerOrder);
                            page = PathManager.getProperty(PathManager.COURIER_PAGE);
                            break;
                        case "customer":
                            page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
                            break;
                    }
                } else {
                    request.setAttribute(MESSAGE, Message.getInstanse().getMessage());
                    page = PathManager.getProperty(PathManager.SIGN_IN_PAGE);
                }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(PAGE_ATTRIBUTE, page);
        return page;
    }


}
