package com.epam.couriers.dao.impl;

import com.epam.couriers.dao.CustomerDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CustomerOrder;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomerDAOImplTest {

    @Test
    public void testGet() throws DAOException {
        //given
        int orderId = 3;
        CustomerDAO dao = new CustomerDAOImpl();
        //when
        CustomerOrder order = dao.get(3);
        //then
        System.out.println(order);
    }
}