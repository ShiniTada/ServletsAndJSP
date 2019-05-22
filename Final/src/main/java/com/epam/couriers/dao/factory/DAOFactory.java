package com.epam.couriers.dao.factory;

import com.epam.couriers.dao.*;
import com.epam.couriers.dao.impl.*;

public class DAOFactory {

    private static UserDAO userDAO = new UserDAOImpl();
    private static CourierDAO courierDAO = new CourierDAOImpl();
    private static TransportDAO transportDAO = new TransportDAOImpl();
    private static GoodsDAO goodsDAO = new GoodsDAOImpl();
    private static CustomerDAO customerDAO = new CustomerDAOImpl();


    public static UserDAO getUserDAO() {
        return userDAO;
    }


    public static CourierDAO getCourierDAO() {
        return courierDAO;
    }

    public static TransportDAO getTransportDAO() {
        return transportDAO;
    }

    public static GoodsDAO getGoodsDAO() {
        return goodsDAO;
    }

    public static CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    private DAOFactory() {
    }
}
