package com.epam.couriers.dao.impl;

import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDAOImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImplTest.class);
    @Test
    public void testAuthorization() throws ServiceException {
        //given
        User user1 = new User();
        user1.setLogin("admin");
        String hashPassword1 = DigestUtils.md5Hex("admin");
        user1.setPassword(hashPassword1);
        user1.setId(1);
        user1.setLogin("Administrator");
        String role = RoleEnum.toEnumFormat("admin");
        user1.setRole(RoleEnum.valueOf(role));

        //when
        User user = new User();
        user.setLogin("admin");
        String hashPassword = DigestUtils.md5Hex("admin");
        user.setPassword(hashPassword);
        UserDAO userDAOImpl = DAOFactory.getUserDAO();
        try {
            user = userDAOImpl.authorization(user);
            if (user == null) {
                LOGGER.warn("Incorrect login or password");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error access database", e);
        }

        //then
        LOGGER.info(user);
        Assert.assertEquals(user1, user);
    }
}