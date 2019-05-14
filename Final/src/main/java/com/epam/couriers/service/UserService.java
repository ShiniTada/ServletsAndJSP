package com.epam.couriers.service;

import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;

public interface UserService {
    /**
     * Authorizes user
     *
     * @param login of a user
     * @param password of a user
     * @return {@link User} entity of authorized user.
     *
     * @throws ServiceException     if error happens during execution
     */
    User logIn(String login, String password) throws ServiceException;

    /**
     * Registers courier
     *
     * @param login of a courier
     * @param password of a courier
     ** @return {@link User} entity of registered user.
     *
     * @throws ServiceException     if error happens during execution
     */
    User courierRegistration(String login, String password) throws ServiceException;

}
