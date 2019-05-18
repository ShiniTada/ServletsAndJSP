package com.epam.couriers.service.impl;

import com.epam.couriers.dao.CourierDAO;
import com.epam.couriers.dao.CustomerDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.dao.factory.DAOFactory;
import com.epam.couriers.dao.impl.CourierDAOImpl;
import com.epam.couriers.dao.manager.TransactionManager;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;
import com.epam.couriers.entity.StatusEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.CustomerService;
import com.epam.couriers.service.exception.ServiceException;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public void addCustomerOrder(int customerId, int courierId, String whatDeliver, String from, String to, String date, String numberOfGoods, String weight) throws ServiceException {
        int price = calculatePrice(numberOfGoods, weight);
        CustomerOrder order = new CustomerOrder(from, to, date, StatusEnum.POSTED, whatDeliver, new User(customerId), new User(courierId), price);
        TransactionManager transactionManager = new TransactionManager();
        try {
            CustomerDAO customerDAO = DAOFactory.getCustomerDAO();
            transactionManager.beginTransaction(customerDAO);
            order = customerDAO.insert(order);
            customerDAO.insertToCourierHasCustomerOrder(courierId, order.getId());
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

    private int calculatePrice(String numberOfGoods, String weight) {
        int partOneOfPrice = 0;
        int partTwoOfPrice = 0;
        if(numberOfGoods.equals("lessThree")) {
            partOneOfPrice = 5;
        }
        if(numberOfGoods.equals("threeTen")) {
            partOneOfPrice = 10;
        }
        if(numberOfGoods.equals("moreTen")) {
            partOneOfPrice = 20;
        }
        if(weight.equals("lessThreeKg")) {
            partTwoOfPrice = 5;
        }
        if(weight.equals("threeTenKg")) {
            partTwoOfPrice = 10;
        }
        if(weight.equals("moreTenKg")) {
            partTwoOfPrice = 20;
        }
        return partOneOfPrice + partTwoOfPrice;
    }


    @Override
    public void setMarks(String courierLogin, int quality, int politeness, int punctuality) throws ServiceException {
        double normQuality = (double) quality * 10.0;
        double normPoliteness = (double) politeness * 10.0;
        double normPunctuality = (double) punctuality * 10.0;
        double normCommon = calculateCommonMark(normQuality, normPoliteness, normPunctuality);

        CourierDAO courierDAO = new CourierDAOImpl();
        TransactionManager transactionManager = new TransactionManager();
        try {
            transactionManager.beginTransaction(courierDAO);

            CourierRecord courierRecord = courierDAO.getMarksAndIdOfOneCourier(courierLogin);
            double newQuality = calculateNewMark(courierRecord.getMarkQuality(), courierRecord.getVotes(), normQuality);
            double newPoliteness = calculateNewMark(courierRecord.getMarkPoliteness(), courierRecord.getVotes(), normPoliteness);
            double newPunctuality = calculateNewMark(courierRecord.getMarkPunctuality(), courierRecord.getVotes(), normPunctuality);
            double newCommon = calculateNewMark(courierRecord.getMarkCommon(), courierRecord.getVotes(), normCommon);
            int newVotesNumber = courierRecord.getVotes() + 1;
            courierDAO.updateMarks(courierRecord.getId(), newQuality, newPoliteness, newPunctuality, newCommon, newVotesNumber);
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

    private double calculateCommonMark(double quality, double politeness, double punctuality) {
        return (quality + politeness + punctuality)/3.0;
    }

    private double calculateNewMark(double average, int votesNumber, double customersMark) {
        double mark = ((average * votesNumber) + customersMark)/(votesNumber + 1.0);
        return Math.round(mark * 100.0) / 100.0;
    }

    @Override
    public void setOrderCompleted(int orderId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            CustomerDAO customerDAO = DAOFactory.getCustomerDAO();
            transactionManager.beginTransaction(customerDAO);
            customerDAO.completeOrder(orderId);
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
