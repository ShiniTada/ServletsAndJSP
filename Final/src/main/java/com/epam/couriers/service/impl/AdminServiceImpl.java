package com.epam.couriers.service.impl;

import com.epam.couriers.dao.*;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public List<User> getAllUsers() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            UserDAO userDAO = DAOFactory.getUserDAO();
            transactionManager.beginTransaction(userDAO);
            List<User> users = userDAO.getAllUsers();
            transactionManager.commit();
            transactionManager.endTransaction();
            return  users;
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

    @Override
    public void acceptCourier(int courierRecordId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
           courierDAO.acceptCourier(courierRecordId);
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

    @Override
    public void rejectCourier(int courierRecordId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            courierDAO.rejectCourier(courierRecordId);
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
