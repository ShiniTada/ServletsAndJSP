package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.AdminDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AdminDAO {


    private static final String SQL_GET_ORDERS_INF = "SELECT co.orderId, us.login AS customer, u.login AS courier, co.from, " +
            "co.to, co.introductionDate, co.goodsDescription, co.price, co.status from CustomerOrder co " +
            "INNER JOIN User us ON us.userId = co.customerId " +
            "INNER JOIN courier_has_CustomerOrder chc ON co.orderId = chc.orderId " +
            "INNER JOIN User u ON chc.courierId = u.userId;";

    @Override
    public List<CustomerOrder> getCustomerOrders() throws DAOException {
        List<CustomerOrder> listOrders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ORDERS_INF)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CustomerOrder order = new CustomerOrder();
                order.setId(rs.getInt(GeneralConstant.ORDER_ID));
                order.setCustomer(new User(rs.getString("customer")));
                order.setCourier(new User(rs.getString("courier")));
                order.setFrom(rs.getString(GeneralConstant.FROM));
                order.setTo(rs.getString(GeneralConstant.TO));
                order.setIntroductionDate(rs.getString(GeneralConstant.INTRODUCTION_DATE));
                order.setGoodsDescription(rs.getString(GeneralConstant.GOOD_DESCRIPTION));
                order.setPrice(rs.getInt(GeneralConstant.PRICE));
                String status = StatusEnum.toEnumFormat(rs.getString(GeneralConstant.STATUS));
                order.setStatus(StatusEnum.valueOf(status));
                listOrders.add(order);
            }
            return listOrders;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }



}
