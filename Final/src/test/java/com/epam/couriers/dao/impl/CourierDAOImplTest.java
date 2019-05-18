package com.epam.couriers.dao.impl;

import com.epam.couriers.dao.CourierDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CourierDAOImplTest {

    @Test
    public void testGet() throws ServiceException {
        //given
        int userId = 2;
        int expectedCourierId = 1;
        //when
        CourierService courierService = new CourierServiceImpl();
        CourierRecord cr = courierService.getCourierRecord(userId);
        System.out.println(cr);
        int actualCourierId = cr.getId();
        //then
        Assert.assertEquals(expectedCourierId, actualCourierId);

    }

    @Test
    public void testGetAllOrderId() throws DAOException {
        //given
        int courierId = 4;
        CourierDAO courierDAO = new CourierDAOImpl();
        //when
        System.out.println(courierDAO.getAllOrderId(courierId));
    }
}