package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import com.epam.couriers.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Sign in user on site
 */
public class SignInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);
//    private static final int  LIVE_TIME = 60 * 10;



    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = PathManager.getProperty(PathManager.ERROR_PAGE_404);
        HttpSession session = request.getSession();
        try {
            User user;
                UserService userService = new UserServiceImpl();
                user = userService.logIn(request.getParameter(GeneralConstant.USER_LOGIN), request.getParameter(GeneralConstant.USER_PASSWORD));
                if (user != null) {
                    session.removeAttribute(GeneralConstant.LIST_USERS);
                    session.setAttribute(GeneralConstant.USER, user);
//                    session.setMaxInactiveInterval(LIVE_TIME);

                    List<CustomerOrder> listCustomerOrder;
                    List<CustomerOrder> existedOrders;
                    List<CustomerOrder>  completedOrders;
                    CourierService courierService;
                    LOGGER.debug("User \"" + user.getLogin() + "\" signed in");
                    switch (user.getRole().getValue()) {
                        case "admin":
                            page = PathManager.getProperty(PathManager.ADMIN_PAGE);
                            break;
                        case "courier":
                            courierService = new CourierServiceImpl();
                            CourierRecord courierRecord = courierService.getCourierRecord(user.getId());
                            List<Transport> transport = courierService.getTransportsOfOneCourier(courierRecord.getId());
                            List<String> listTransport = new ArrayList<>();
                            for (Transport t : transport){
                                listTransport.add(t.getTypeTransport().getValue());
                            }
                            List<Goods> goods = courierService.getGoodsOfOneCourier(courierRecord.getId());
                            List<String> listGoods = new ArrayList<>();
                            for (Goods g : goods){
                                listGoods.add(g.getTypeGoods().getValue());
                            }
                            listCustomerOrder = courierService.getCustomerOrdersOfOneCourier(user.getLogin());
                            existedOrders = new ArrayList<>();
                            completedOrders = new ArrayList<>();
                            for(CustomerOrder order : listCustomerOrder){
                                if(order.getStatus().getValue().equals("posted") || order.getStatus().getValue().equals("delivered")){
                                    existedOrders.add(order);
                                }else {
                                    completedOrders.add(order);
                                }
                            }
                            session.setAttribute(GeneralConstant.COURIER_RECORD, courierRecord);
                            session.setAttribute(GeneralConstant.LIST_TRANSPORT, listTransport);
                            session.setAttribute(GeneralConstant.LIST_GOODS, listGoods);
                            session.setAttribute(GeneralConstant.EXISTED_ORDERS, existedOrders);
                            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, completedOrders);
                            page = PathManager.getProperty(PathManager.COURIER_PAGE);
                            break;
                        case "customer":
                            courierService = new CourierServiceImpl();
                            List<CustomerOrder> orders = courierService.getCustomerOrdersOfOneCustomer(user.getLogin());

                            existedOrders = new ArrayList<>();
                            completedOrders = new ArrayList<>();
                            for(CustomerOrder order : orders){
                                if(order.getStatus().getValue().equals("posted") || order.getStatus().getValue().equals("delivered")){
                                    existedOrders.add(order);
                                }else {
                                    completedOrders.add(order);
                                }
                            }
                            session.setAttribute(GeneralConstant.EXISTED_ORDERS, existedOrders);
                            session.setAttribute(GeneralConstant.COMPLETED_ORDERS, completedOrders);
                            page = PathManager.getProperty(PathManager.CUSTOMER_PAGE);
                            break;
                    }
                } else {
                    request.setAttribute(GeneralConstant.MESSAGE_ATRIBUTE, AllErrorMessages.NOT_CORRECT_LOGIN_OR_PASSWORD);
                    page = PathManager.getProperty(PathManager.SIGN_IN_PAGE);
                }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;
    }


}
