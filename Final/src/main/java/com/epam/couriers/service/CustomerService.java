package com.epam.couriers.service;

import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public interface CustomerService {

    /**
     *
     * Calculate price of order and add customer his order.
     *
     * @param customerId - id of customer id of selected customer
     * @param courierId - id of selected customer
     * @param whatDeliver - what to deliver
     * @param from - from
     * @param to - to
     * @param date - delivery date
     * @param numberOfGoods - number of goods
     * @param weight - weight of parcel
     *
     * @throws ServiceException if error happens during execution
     */
    void addCustomerOrder(int customerId, int courierId, String whatDeliver, String from, String to,
                          String date, String numberOfGoods, String weight) throws ServiceException;


    /**
     * Changes the courier's marks in accordance with the customer's marks.
     *
     * @param courierLogin - courier login
     * @param quality - mark quality
     * @param politeness - mark politeness
     * @param punctuality - mark punctuality
     *
     * @throws ServiceException if error happens during execution
     */
    void setMarks(String courierLogin, int quality, int politeness, int punctuality) throws ServiceException;

    /**
     * Changes the order stratus: delivered -> completed
     */
    void setOrderCompleted(int orderId) throws ServiceException;

    void deleteOrder(int orderId) throws ServiceException;
}
