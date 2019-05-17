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
     * Get courier's records
     *
     * @return {@link List<CourierRecord>} list of courier's records
     *
     * @throws ServiceException if error happens during execution
     */
    List<CourierRecord> getAllCouriersRecords() throws ServiceException;

    /**
     * Get customer's orders
     *
     * @return {@link List<CourierRecord>} list of courier's records
     *
     * @throws ServiceException if error happens during execution
     */
    List<CustomerOrder> getCustomerOrders() throws ServiceException;
    /**
     * Get courier's transports
     *
     * @return {@link List<Transport>} list of courier's transports
     *
     * @throws ServiceException if error happens during execution
     */
    List<Transport> getCourierTransports() throws ServiceException;

    /**
     * Get courier's delivering goods
     *
     * @return {@link List<Goods>} list of courier's delivering goods
     *
     * @throws ServiceException if error happens during execution
     */
    List<Goods> getCourierGoods() throws ServiceException;

    /**
     * Set active status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws ServiceException if error happens during execution
     */
    void acceptCourier(int courierRecordId) throws ServiceException;

    /**
     * Set not available status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws ServiceException if error happens during execution
     */
    void rejectCourier(int courierRecordId) throws ServiceException;
}
