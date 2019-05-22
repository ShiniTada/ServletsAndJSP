package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

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
     * Get list of customer's orders.
     *
     * This method get only:
     *      *               <br> - {@link CustomerOrder #orderId}
     *      *               <br> - {@link User #name} - customer
     *      *               <br> - {@link User #name} - courier
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #goodsDescription}
     *      *               <br> - {@link CustomerOrder #price}
     *      *               <br> - {@link CustomerOrder #status}
     *
     * @return  list of customer's orders
     * @throws DAOException if something went wrong
     */
    public abstract List<CustomerOrder> getCustomerOrders() throws DAOException;

    /**
     * Get {@code countOfOrdersOnOnePage} courier's records with active
     * and blocked status, which started with {@code startIndex}.
     *
     * This method get only:
     *      *               <br> - {@link CustomerOrder #orderId}
     *      *               <br> - {@link User #name} - customer
     *      *               <br> - {@link User #name} - courier
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #goodsDescription}
     *      *               <br> - {@link CustomerOrder #price}
     *      *               <br> - {@link CustomerOrder #status}
     *
     * @return  list of customer's orders
     * @throws DAOException if something went wrong
     */
    public abstract List<CustomerOrder> findWithLimitCustomerOrders(int startIndex, int countOfOrdersOnOnePage) throws DAOException;


    /**
     * @return count of customer orders
     * @throws ServiceException if error happens during execution
     */
    public abstract int findTotalCountOfCustomerOrders()throws ServiceException, DAOException;

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

    /**
     * Delete customer order by id.
     *
     * @param orderId - order id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void deleteOrder(int orderId)throws DAOException;

    /**
     * Delete bundle courier_has_customerOrder by order id.
     *
     * @param orderId - order id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void deleteBundle(int orderId)throws DAOException;


    @Override
    public void delete(int id){

    }
}
