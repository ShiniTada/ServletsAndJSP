package com.epam.couriers.dao.impl;

import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.service.CourierSevrice;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CourierDAOImplTest {

    @Test
    public void testGet() throws ServiceException {
        //given
        int userId = 2;
        int expectedCourierId = 1;
        //when
        CourierSevrice courierSevrice = new CourierServiceImpl();
        CourierRecord cr = courierSevrice.getCourierRecord(userId);
        System.out.println(cr);
        int actualCourierId = cr.getId();
        //then
        Assert.assertEquals(expectedCourierId, actualCourierId);

    }
}