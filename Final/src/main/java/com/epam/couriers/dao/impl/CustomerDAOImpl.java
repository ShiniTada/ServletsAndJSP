package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.CustomerDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl extends CustomerDAO {
    private static final String SQL_GET_CUSTOMER_ORDER_INF = "SELECT co.from, co.to, co.customerId, co.introductionDate, " +
            "co.goodsDescription, co.status FROM customerorder co WHERE co.orderId= ?";

    @Override
    public CustomerOrder get(int orderId) throws DAOException {
        CustomerOrder order = new CustomerOrder();
        order.setId(orderId);
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CUSTOMER_ORDER_INF)) {
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                order.setFrom(rs.getString(GeneralConstant.FROM));
                order.setTo(rs.getString(GeneralConstant.TO));
                order.setCustomer(new User(rs.getInt(GeneralConstant.CUSTOMER_ID)));
                order.setIntroductionDate(rs.getString(GeneralConstant.INTRODUCTION_DATE));
                order.setGoodsDescription(rs.getString(GeneralConstant.GOOD_DESCRIPTION));
                String status = StatusEnum.toEnumFormat(rs.getString(GeneralConstant.STATUS));
                order.setStatus(StatusEnum.valueOf(status));
            }
            return order;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
