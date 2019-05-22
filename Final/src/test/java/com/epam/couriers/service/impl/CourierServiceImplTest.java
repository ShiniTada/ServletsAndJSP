package com.epam.couriers.service.impl;

import com.epam.couriers.entity.*;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CourierServiceImplTest {

    @Test
    public void testGetCourierRecord() throws ServiceException {
        //given
        int userId = 2;
        int expectedCourierId = 1;
        //when
        CourierService courierService = new CourierServiceImpl();
        CourierRecord cr = courierService.getCourierRecord(userId);
        //then
        assertEquals(cr.getId(), expectedCourierId);
    }

    @Test
    public void testGetTransportsOfOneCourier() throws ServiceException {
        //given
        int courierRecordId = 1;
        List<TransportEnum> expected = Arrays.asList(TransportEnum.CYCLE, TransportEnum.MOTORCYCLE);
        //when
        CourierService courierService = new CourierServiceImpl();
        List<Transport> actualTransport = courierService.getTransportsOfOneCourier(courierRecordId);
        List<TransportEnum> actual = new ArrayList<>();
        for(Transport t :actualTransport){
            actual.add(t.getTypeTransport());
        }
        //then
        assertEquals(actual, expected);
    }

    @Test
    public void testGetGoodsOfOneCourier() throws ServiceException {
        //given
        int courierRecordId = 2;
        List<GoodsEnum> expected = Arrays.asList(GoodsEnum.FOOD, GoodsEnum.EASY_TO_BEAT);
        //when
        CourierService courierService = new CourierServiceImpl();
        List<Goods> actualGoods = courierService.getGoodsOfOneCourier(courierRecordId);
        List<GoodsEnum> actual = new ArrayList<>();
        for(Goods g :actualGoods){
            actual.add(g.getTypeGoods());
        }
        //then
        assertEquals(actual, expected);
    }
}