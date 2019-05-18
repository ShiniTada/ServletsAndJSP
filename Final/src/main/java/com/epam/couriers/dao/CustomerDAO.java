package com.epam.couriers.dao;

import com.epam.couriers.dao.BaseDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;

public abstract class CustomerDAO extends BaseDAO<CustomerOrder> {

    /**
     * Get courierRecord  from database
     *
     * @param orderId - order id
     * This method get only:
     * where must be set following properties:
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #goodDescription}
     *      *               <br> - {@link CustomerOrder #customerId}
     *      *               <br> - {@link CustomerOrder #status}
     * @return {@link CustomerOrder } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CustomerOrder get(int orderId) throws DAOException;


    /**
     * Inserts customer order into database
     *
     * @param customerOrder where must be set following properties:
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #status}
     *      *               <br> - {@link CustomerOrder #goodDescription}
     *      *               <br> - {@link CustomerOrder #customerId}
     *      *               <br> - {@link CustomerOrder #price}
     * @return {@link CustomerOrder } with all filled properties
     * @throws DAOException if something went wrong
     */
    @Override
    public abstract CustomerOrder insert(CustomerOrder customerOrder) throws DAOException;

    /**
     * Inserts bundle between courier and customer order into database
     *
     * @param customerId - customer id
     * @param orderId - order id
     * @throws DAOException if something went wrong
     */
    public abstract void insertToCourierHasCustomerOrder(int customerId, int orderId) throws DAOException;


    /**
     * Change status of record: posted -> delivered
     *
     * @param orderId - order id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void acceptOrder(int orderId)throws DAOException;

    /**
     * Change status of record: posted -> denied
     *
     * @param orderId - order id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void rejectOrder(int orderId)throws DAOException;

    /**
     * Change status of record: delivered -> completed
     *
     * @param orderId - order id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void completeOrder(int orderId)throws DAOException;


    @Override
    public void delete(int id) throws DAOException {

    }
}
