package com.epam.couriers.dao.impl;

import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;
import org.testng.annotations.Test;

import java.util.List;

public class AdminDAOImplTest {

    @Test
    public void testGetAllCouriersRecords() throws ServiceException {
            AdminService adminService = new AdminServiceImpl();
            List<CourierRecord> listCourierRecords = adminService.getAllCouriersRecords();
            for(CourierRecord cr : listCourierRecords) {
                System.out.println(cr);
            }

    }
}