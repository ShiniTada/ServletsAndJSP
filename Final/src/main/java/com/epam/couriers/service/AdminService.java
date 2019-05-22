package com.epam.couriers.service;

import com.epam.couriers.entity.*;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public interface AdminService {

    /**
     * Gets users
     *
     * @return {@link List<User>} list of users
     *
     * @throws ServiceException  if error happens during execution
     */
    List<User> getAllUsers() throws ServiceException;
    /**
     * Get courier's new records
     *
     * @return {@link List<CourierRecord>} list of new courier's records
     *
     * @throws ServiceException if error happens during execution
     */
    List<CourierRecord> getNewCouriersRecords() throws ServiceException;

    /**
     * Get {@code countOfCouriersOnOnePage} courier's records with active
     * and blocked status, which started with {@code startIndex}
     *
     * @return {@link List<CourierRecord>} list of courier's records
     *
     * @throws ServiceException if error happens during execution
     */
    List<CourierRecord> findWithLimitCouriersRecords(int startIndex, int countOfCouriersOnOnePage) throws ServiceException;


    /**
     * Get {@code countOfCouriersOnOnePage} courier's records with active
     * status, which started with {@code startIndex}
     *
     * @return {@link List<CourierRecord>} list of courier's records
     *
     * @throws ServiceException if error happens during execution
     */
    List<CourierRecord> getCouriersRecordsForCustomer() throws ServiceException;

    /**
     * Get customer's orders
     *
     * @return {@link List<CustomerOrder>} list of customer's orders
     *
     * @throws ServiceException if error happens during execution
     */
    List<CustomerOrder> findWithLimitCustomerOrders(int startIndex, int countOfOrdersOnOnePage) throws ServiceException;

    /**
     * Get customer's goods
     *
     * @return {@link List<Goods>} list of courier's goods
     *
     * @throws ServiceException if error happens during execution
     */
    List<Goods> findWithLimitGoods(int startIndex, int countOfGoodsOnOnePage) throws ServiceException;


    /**
     * Get customer's transport
     *
     * @return {@link List<Transport>} list of courier's transport
     *
     * @throws ServiceException if error happens during execution
     */
    List<Transport> findWithLimitTransport(int startIndex, int countOfTransportOnOnePage) throws ServiceException;

    /**
     * @return count of courier records with active and blocked status
     * @throws ServiceException if error happens during execution
     */
    int findTotalCountOfCourierRecords()throws ServiceException;

    /**
     * @return count of courier records with active status
     * @throws ServiceException if error happens during execution
     */
    int findTotalCountOfCourierRecordsForCouriers()throws ServiceException;

    /**
     * @return count of customers orders
     * @throws ServiceException if error happens during execution
     */
    int findTotalCountOfCustomerOrders()throws ServiceException;

    /**
     * @return count of goods
     * @throws ServiceException if error happens during execution
     */
    int findTotalCountOfGoods()throws ServiceException;

    /**
     * @return count of transport
     * @throws ServiceException if error happens during execution
     */
    int findTotalCountOfTransport()throws ServiceException;


    /**
     * Set active status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws ServiceException if error happens during execution
     */
    void acceptCourier(int courierRecordId) throws ServiceException;

    /**
     * Set not available status to courier and delete his transport, goods
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws ServiceException if error happens during execution
     */
    void rejectCourier(int courierRecordId) throws ServiceException;

    /**
     * Set blocked status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws ServiceException if error happens during execution
     */
    void blockCourier(int courierRecordId) throws ServiceException;
}
