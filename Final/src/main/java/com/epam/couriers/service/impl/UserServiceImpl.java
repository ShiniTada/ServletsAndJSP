package com.epam.couriers.service.impl;

import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.errormessage.AllErrorMessages;
import com.epam.couriers.service.errormessage.Message;
import com.epam.couriers.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Override
    public User logIn(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        String hashPassword = DigestUtils.md5Hex(password);
        user.setPassword(hashPassword);
        TransactionManager transactionManager = new TransactionManager();
        try {
            UserDAO userDAO = DAOFactory.getUserDAO();
            transactionManager.beginTransaction(userDAO);
            user = userDAO.authorization(user);
            if (user != null) {
                transactionManager.commit();
                transactionManager.endTransaction();
                return user;
            } else {
                Message.getInstanse().setMessage(AllErrorMessages.NOT_CORRECT_LOGIN_OR_PASSWORD);
                LOGGER.debug("Incorrect login or password");
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
            UserDAO userDAO = DAOFactory.getUserDAO();
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

}
