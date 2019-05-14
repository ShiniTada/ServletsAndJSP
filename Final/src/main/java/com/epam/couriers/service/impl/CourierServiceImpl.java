package com.epam.couriers.service.impl;

import com.epam.couriers.dao.*;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.CourierSevrice;
import com.epam.couriers.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class CourierServiceImpl implements CourierSevrice {

    @Override
    public CourierRecord addCourierRecord(int courierId) throws ServiceException {
        CourierRecord courierRecord = new CourierRecord();
        courierRecord.setCourier(new User(courierId));
        courierRecord.setStatus(0);
        courierRecord.setMarkPoliteness(0);
        courierRecord.setMarkPunctuality(0);
        courierRecord.setMarkQuality(0);
        courierRecord.setMarkCommon(0);
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            courierRecord = courierDAO.insert(courierRecord);
            transactionManager.commit();
            transactionManager.endTransaction();
            return courierRecord;
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
    public CourierRecord getCourierRecord(int courierId) throws ServiceException {
        CourierRecord courierRecord = new CourierRecord();
        courierRecord.setCourier(new User(courierId));
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            courierRecord = courierDAO.get(courierRecord);
            transactionManager.commit();
            transactionManager.endTransaction();
            return courierRecord;
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
    public void addAllTransports(int courierRecordId, List<String> transports) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            TransportDAO transportDAO = DAOFactory.getTransportDAO();
            transactionManager.beginTransaction(transportDAO);
            for (String transport : transports) {
                transportDAO.insert(courierRecordId, transport);
            }
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
    public List<Transport> getTransportsOfOneCourier(int courierRecordId) throws ServiceException {
            TransactionManager transactionManager = new TransactionManager();
            try {
                TransportDAO transportDAO = DAOFactory.getTransportDAO();
                transactionManager.beginTransaction(transportDAO);
                List<Transport> transport = transportDAO.getTransportsOfOneCourier(courierRecordId);
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
    public void addAllGoods(int courierRecordId, List<String> goods) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            GoodsDAO transportDAO = DAOFactory.getGoodsDAO();
            transactionManager.beginTransaction(transportDAO);
            for (String good : goods) {
                transportDAO.insert(courierRecordId, good);
            }
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
    public List<Goods> getGoodsOfOneCourier(int courierRecordId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            GoodsDAO goodsDAO = DAOFactory.getGoodsDAO();
            transactionManager.beginTransaction(goodsDAO);
            List<Goods> goods = goodsDAO.getGoodsOfOneCourier(courierRecordId);
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
    public List<CustomerOrder> getCustomerOrdersOfOneCourier(int courierId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CourierDAO courierDAO = DAOFactory.getCourierDAO();
            transactionManager.beginTransaction(courierDAO);
            List<Integer> allOrderId = courierDAO.getAllOrderId(courierId);
            if(allOrderId == null) {
                return  null;
            }
            CustomerDAO customerDAO = DAOFactory.getCustomerDAO();
            List<CustomerOrder> listOrder = new ArrayList<>();
            for(int orderId : allOrderId){
                CustomerOrder order = customerDAO.get(orderId);
                listOrder.add(order);
            }
            transactionManager.commit();
            transactionManager.endTransaction();
            return listOrder;

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
