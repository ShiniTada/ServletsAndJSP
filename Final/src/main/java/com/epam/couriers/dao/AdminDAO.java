package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;

import java.util.List;

public abstract class AdminDAO extends BaseDAO<User> {


    /**
     * Get list of customer's orderss.
     *
     * This method get only:
     *      *               <br> - {@link CustomerOrder #orderId}
     *      *               <br> - {@link User #name} - customer
     *      *               <br> - {@link User #name} - courier
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #status}
     *
     * @return  listOrders - list of customer's orders
     * @throws DAOException if something went wrong
     */
    public abstract List<CustomerOrder> getCustomerOrders() throws DAOException;


    @Override
    public User insert(User entity) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {

    }
}
