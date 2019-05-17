package com.epam.couriers.service;

import com.epam.couriers.entity.RoleEnum;
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
     * Registers user
     *
     * @param login of a user
     * @param password of a user
     ** @return {@link User} entity of registered user.
     *
     * @throws ServiceException     if error happens during execution
     */
    User registration(String login, String password, RoleEnum role) throws ServiceException;


}
