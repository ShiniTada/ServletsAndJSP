package com.epam.couriers.service.impl;

import com.epam.couriers.dao.AdminDAO;
import com.epam.couriers.dao.CourierDAO;
import com.epam.couriers.dao.GoodsDAO;
import com.epam.couriers.dao.TransportDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<CourierRecord> getNewCouriersRecords() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            List<CourierRecord> courierRecords = courierDAO.getNewCouriersRecords();
            transactionManager.commit();
            transactionManager.endTransaction();
            return  courierRecords;
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
    public List<CourierRecord> getAllCouriersRecords() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            List<CourierRecord> courierRecords = courierDAO.getAllCouriersRecords();
            transactionManager.commit();
            transactionManager.endTransaction();
            return  courierRecords;
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
    public List<CustomerOrder> getCustomerOrders() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            AdminDAO adminDAO = DAOFactory.getAdminDAO();
            transactionManager.beginTransaction(adminDAO);
            List<CustomerOrder> customerOrders = adminDAO.getCustomerOrders();
            transactionManager.commit();
            transactionManager.endTransaction();
            return  customerOrders;
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
    public List<Transport> getCourierTransports() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            TransportDAO transportDAO = DAOFactory.getTransportDAO();
            transactionManager.beginTransaction(transportDAO);
            List<Transport> transport = transportDAO.getCourierTransports();
            transactionManager.commit();
            transactionManager.endTransaction();
            return  transport;

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
    public List<Goods> getCourierGoods() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            GoodsDAO goodsDAO = DAOFactory.getGoodsDAO();
            transactionManager.beginTransaction(goodsDAO);
            List<Goods> goods = goodsDAO.getCourierGoods();
            transactionManager.commit();
            transactionManager.endTransaction();
            return goods;

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
