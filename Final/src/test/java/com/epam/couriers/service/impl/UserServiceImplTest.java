package com.epam.couriers.service.impl;

import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

public class UserServiceImplTest {

    private static final UserDAO EMPTY_USER_DAO_MOCK = mock(UserDAO.class);
    private final UserService userServiceWithEmptyMocks = new UserServiceImpl(EMPTY_USER_DAO_MOCK);

    @Test
    public void testCorrectRegistration() throws ServiceException, DAOException {
        User user = new User("login","password", RoleEnum.CUSTOMER);
        userServiceWithEmptyMocks.registration(user.getLogin(),user.getPassword(),
                user.getRole());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        verify(EMPTY_USER_DAO_MOCK).insert(user);
    }

    @Test
    public void testLogInAnyUser() throws ServiceException, DAOException {
        User user = new User("login");
        user.setPassword(DigestUtils.md5Hex("password"));
        userServiceWithEmptyMocks.logIn("login", "password");
        verify(EMPTY_USER_DAO_MOCK).authorization(user);
    }

    @Test
    public void testCorrectChangePasswordOfAnyUser() throws ServiceException, DAOException {
        User user = new User(100);
        user.setPassword("password");
        userServiceWithEmptyMocks.changePassword(user.getId(), "newPassword");
        user.setPassword(DigestUtils.md5Hex("newPassword"));
        verify(EMPTY_USER_DAO_MOCK).update(user);
    }


    @Test
    public void testLogInAdminWithCheckingInDataBase() throws ServiceException {
        //given
        User expected = new User(1, "admin", DigestUtils.md5Hex("admin"), RoleEnum.ADMIN);
        //when
        UserService userService = new UserServiceImpl(DAOFactory.getUserDAO());
        String login = "admin", password = "admin";
        User actual = userService.logIn(login , password);
        //then
        assertEquals(actual, expected);
    }


}