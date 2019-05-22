package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.CustomerDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CustomerDAOImpl extends CustomerDAO {

    private static final String SQL_GET_CUSTOMER_ORDER_INF = "SELECT co.from, co.to, co.customerId, co.introductionDate, " +
            "co.goodsDescription, co.price , co.status from customerorder co WHERE co.orderId = ?;";

    private static final String SQL_GET_ORDERS_INF = "SELECT co.orderId, us.login AS customer, u.login AS courier, co.from, " +
            "co.to, co.introductionDate, co.goodsDescription, co.price, co.status from CustomerOrder co " +
            "INNER JOIN User us ON us.userId = co.customerId " +
            "INNER JOIN courier_has_CustomerOrder chc ON co.orderId = chc.orderId " +
            "INNER JOIN User u ON chc.courierId = u.userId;";

    private static final String SQL_GET_ORDERS_INF_WITH_LIMIT = "SELECT co.orderId, us.login AS customer, u.login AS courier, co.from, " +
            "co.to, co.introductionDate, co.goodsDescription, co.price, co.status from CustomerOrder co " +
            "INNER JOIN User us ON us.userId = co.customerId " +
            "INNER JOIN courier_has_CustomerOrder chc ON co.orderId = chc.orderId " +
            "INNER JOIN User u ON chc.courierId = u.userId LIMIT ?, ?;";

    private static final String SQL_GET_COUNT_OF_CUSTOMER_ORDERS = "SELECT COUNT(orderId) " +
            "FROM customerorder ORDER BY orderId;";

    private static final String SQL_ADD_NEW_CUSTOMER_ORDER = "INSERT INTO customerorder (`from`, `to`, introductionDate, " +
            "status, goodsDescription, price, customerId) VALUES (?,?,?,?,?,?,?);";

    private static final String SQL_ADD_NEW_COURIER_CUSTOMER_ORDER_BUNDLE = "INSERT INTO courier_has_customerorder (courierId, orderId) VALUES (?,?);";

    private static final String SQL_UPDATE_CUSTOMER_ORDER_STATUS = "UPDATE customerorder SET status= ? WHERE orderId = ?";

    private static final String SQL_DELETE_CUSTOMER_ORDER = "DELETE FROM customerorder WHERE orderId = ?";

    private static final String SQL_DELETE_COURIER_HAS_CUSTOMER_ORDER_BUNDLE = "DELETE FROM courier_has_customerorder WHERE orderId = ?";



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
                order.setPrice(rs.getInt(GeneralConstant.PRICE));
                String status = StatusEnum.toEnumFormat(rs.getString(GeneralConstant.STATUS));
                order.setStatus(StatusEnum.valueOf(status));
            }
            return order;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

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

    @Override
    public List<CustomerOrder> findWithLimitCustomerOrders(int startIndex, int countOfOrdersOnOnePage) throws DAOException {
        List<CustomerOrder> listOrders = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ORDERS_INF_WITH_LIMIT)) {
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, countOfOrdersOnOnePage);
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

    @Override
    public int findTotalCountOfCustomerOrders() throws DAOException {
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_CUSTOMER_ORDERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public CustomerOrder insert(CustomerOrder customerOrder) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEW_CUSTOMER_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customerOrder.getFrom());
            preparedStatement.setString(2, customerOrder.getTo());
            preparedStatement.setString(3, customerOrder.getIntroductionDate());
            preparedStatement.setString(4, customerOrder.getStatus().getValue());
            preparedStatement.setString(5, customerOrder.getGoodsDescription());
            preparedStatement.setInt(6, customerOrder.getPrice());
            preparedStatement.setInt(7, customerOrder.getCustomer().getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating customerOrder failed, no rows affected.");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                customerOrder.setId(keys.getInt(1));
            } else {
                throw new SQLException("Creating customerOrder failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return customerOrder;
    }

    @Override
    public void insertToCourierHasCustomerOrder(int customerId, int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEW_COURIER_CUSTOMER_ORDER_BUNDLE)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating bundle failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void acceptOrder(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_ORDER_STATUS)) {
            preparedStatement.setString(1, GeneralConstant.DELIVERED);
            preparedStatement.setInt(2, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Changing status failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void rejectOrder(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_ORDER_STATUS)) {
            preparedStatement.setString(1, GeneralConstant.DENIED);
            preparedStatement.setInt(2, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Changing status failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void completeOrder(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_ORDER_STATUS)) {
            preparedStatement.setString(1, GeneralConstant.COMPLETED);
            preparedStatement.setInt(2, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Changing status failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteOrder(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CUSTOMER_ORDER)) {
            preparedStatement.setInt(1, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Deleting customer order '"+ orderId + "' failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteBundle(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURIER_HAS_CUSTOMER_ORDER_BUNDLE)) {
            preparedStatement.setInt(1, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Deleting customer order '"+ orderId + "' failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
