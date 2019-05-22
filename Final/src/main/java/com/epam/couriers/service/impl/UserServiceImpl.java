package com.epam.couriers.service.impl;

import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User logIn(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        String hashPassword = DigestUtils.md5Hex(password);
        user.setPassword(hashPassword);
        TransactionManager transactionManager = new TransactionManager();
        try {
            transactionManager.beginTransaction(userDAO);
            user = userDAO.authorization(user);
            if (user != null) {
                transactionManager.commit();
                transactionManager.endTransaction();
                return user;
            } else {
                LOGGER.info("Incorrect login or password");
                return null;
            }
        } catch (DAOException e) {
            try {
                transactionManager.rollback();

                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return null;
    }

    @Override
    public User registration(String login, String password, RoleEnum role) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setRole(role);
        String hashPassword = DigestUtils.md5Hex(password);
        user.setPassword(hashPassword);
        TransactionManager transactionManager = new TransactionManager();
        try {
            transactionManager.beginTransaction(userDAO);
            user = userDAO.insert(user);
            transactionManager.commit();
            transactionManager.endTransaction();
            return user;
        } catch (DAOException e) {
            try {
                transactionManager.rollback();

                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return null;
    }

    @Override
    public void changePassword(int userId, String newPassword) throws ServiceException {
        User user = new User();
        user.setId(userId);
        String hashPassword = DigestUtils.md5Hex(newPassword);
        user.setPassword(hashPassword);
        TransactionManager transactionManager = new TransactionManager();
        try {
            transactionManager.beginTransaction(userDAO);
            userDAO.update(user);
            transactionManager.commit();
            transactionManager.endTransaction();
        } catch (DAOException e) {
            try {
                transactionManager.rollback();

                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
    }

}
