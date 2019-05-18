package com.epam.couriers.service;

import com.epam.couriers.entity.*;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public interface CourierService {
    /**
     * Registers courier
     *
     * @param courierId - courier id
     * @return {@link CourierRecord} entity of added courier record.
     * @throws ServiceException if error happens during execution
     */
    CourierRecord addCourierRecord(int courierId) throws ServiceException;

    /**
     * Get information about status and marks of courier
     *
     * @param courierId - courier id
     * @return {@link CourierRecord} entity of added courier record.
     * @throws ServiceException if error happens during execution
     */
    CourierRecord getCourierRecord(int courierId) throws ServiceException;



    void acceptOrder(int orderId) throws ServiceException;


    void rejectOrder(int orderId) throws ServiceException;


    /**
     *
     * Get information about marks of courier
     * @param courierRecord - consist only {@code courierRecordId}
     * @return {@link CourierRecord} entity of courier record.
     * @throws ServiceException if error happens during execution
     */
    CourierRecord getCourierRecord(CourierRecord courierRecord) throws ServiceException;

    /**
     * Add to courier his transport
     *
     * @param courierRecordId - courier record id
     * @param transports - list of transport

     * @throws ServiceException if error happens during execution
     */
    void addAllTransports(int courierRecordId, List<String> transports) throws ServiceException;


    /**
     * Get transport which courier may use
     *
     * @param courierRecordId - courier record id

     * @throws ServiceException if error happens during execution
     */
    List<Transport> getTransportsOfOneCourier(int courierRecordId) throws ServiceException;

    /**
     * Add to courier his transport
     *
     * @param courierRecordId - courier record id
     * @param goods - lisy of goods
     *
     * @throws ServiceException if error happens during execution
     */
    void addAllGoods(int courierRecordId, List<String> goods) throws ServiceException;

    /**
     * Get goods which courier may use
     *
     * @param courierRecordId - courier record id
     * @return goods which courier may use
     * @throws ServiceException if error happens during execution
     */
    List<Goods> getGoodsOfOneCourier(int courierRecordId) throws ServiceException;


    /**
     * @param courierLogin - the name of courier
     * @return customer order which courier should does
     * @throws ServiceException if error happens during execution
     */
    List<CustomerOrder>  getCustomerOrdersOfOneCourier(String courierLogin) throws ServiceException;

    /**
     * @param customerLogin - the name of customer
     * @return customer order
     * @throws ServiceException if error happens during execution
     */
    List<CustomerOrder>  getCustomerOrdersOfOneCustomer(String customerLogin) throws ServiceException;
}
