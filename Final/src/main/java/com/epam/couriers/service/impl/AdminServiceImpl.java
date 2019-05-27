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
            return users;
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
            return courierRecords;
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
    public List<CourierRecord> findWithLimitCouriersRecords(int startIndex, int countOfCouriersOnOnePage) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            List<CourierRecord> courierRecords = courierDAO.findWithLimitCouriersRecords(startIndex, countOfCouriersOnOnePage);
            transactionManager.commit();
            transactionManager.endTransaction();
            return courierRecords;
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
    public List<CourierRecord> getCouriersRecordsForCustomer() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            List<CourierRecord> courierRecords = courierDAO.getCouriersRecordsForCustomer();
            transactionManager.commit();
            transactionManager.endTransaction();
            return courierRecords;
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
    public List<CustomerOrder> findWithLimitCustomerOrders(int startIndex, int countOfOrdersOnOnePage) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CustomerDAO customerDAO = DAOFactory.getCustomerDAO();
            transactionManager.beginTransaction(customerDAO);
            List<CustomerOrder> customerOrders = customerDAO.findWithLimitCustomerOrders(startIndex, countOfOrdersOnOnePage);
            transactionManager.commit();
            transactionManager.endTransaction();
            return customerOrders;
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
    public List<Goods> findWithLimitGoods(int startIndex, int countOfGoodsOnOnePage) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            GoodsDAO goodsDAO = DAOFactory.getGoodsDAO();
            transactionManager.beginTransaction(goodsDAO);
            List<Goods> goods = goodsDAO.findWithLimitGoods(startIndex, countOfGoodsOnOnePage);
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
    public List<Transport> findWithLimitTransport(int startIndex, int countOfTransportOnOnePage) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            TransportDAO transportDAO = DAOFactory.getTransportDAO();
            transactionManager.beginTransaction(transportDAO);
            List<Transport> transport = transportDAO.findWithLimitTransport(startIndex, countOfTransportOnOnePage);
            transactionManager.commit();
            transactionManager.endTransaction();
            return transport;
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
    public int findTotalCountOfCourierRecords() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            int count = courierDAO.findTotalCountOfCourierRecords();
            transactionManager.commit();
            transactionManager.endTransaction();
            return count;
        } catch (DAOException e) {
            try {
                transactionManager.rollback();
                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return 0;
    }


    @Override
    public int findTotalCountOfCustomerOrders() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CustomerDAO customerDAO = DAOFactory.getCustomerDAO();
            transactionManager.beginTransaction(customerDAO);
            int count = customerDAO.findTotalCountOfCustomerOrders();
            transactionManager.commit();
            transactionManager.endTransaction();
            return count;
        } catch (DAOException e) {
            try {
                transactionManager.rollback();
                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return 0;
    }

    @Override
    public int findTotalCountOfGoods() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            GoodsDAO goodsDAO = DAOFactory.getGoodsDAO();
            transactionManager.beginTransaction(goodsDAO);
            int count = goodsDAO.findTotalCountOfGoods();
            transactionManager.commit();
            transactionManager.endTransaction();
            return count;
        } catch (DAOException e) {
            try {
                transactionManager.rollback();
                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return 0;
    }

    @Override
    public int findTotalCountOfTransport() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            TransportDAO transportDAO = DAOFactory.getTransportDAO();
            transactionManager.beginTransaction(transportDAO);
            int count = transportDAO.findTotalCountOfTransport();
            transactionManager.commit();
            transactionManager.endTransaction();
            return count;
        } catch (DAOException e) {
            try {
                transactionManager.rollback();
                transactionManager.endTransaction();
            } catch (DAOException ex) {
                throw new ServiceException("Error access database", e);
            }
        }
        return 0;
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
        CourierDAO courierDAO = DAOFactory.getCourierDAO();
        TransportDAO transportDAO = DAOFactory.getTransportDAO();
        GoodsDAO goodsDAO = DAOFactory.getGoodsDAO();
        try {
            transactionManager.beginTransaction(courierDAO);
            transportDAO.delete(courierRecordId);
            goodsDAO.delete(courierRecordId);
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

    @Override
    public void blockCourier(int courierRecordId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CourierDAO courierDAO = DAOFactory.getCourierDAO();
        try {
            transactionManager.beginTransaction(courierDAO);
            courierDAO.blockCourier(courierRecordId);

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
