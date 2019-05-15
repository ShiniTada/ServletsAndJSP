package com.epam.couriers.dao;


import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public  abstract class CourierDAO extends BaseDAO<CourierRecord>{
    /**
     * Inserts courierRecord into database
     *
     * @param courierRecord where must be set following properties:
     *      *               <br> - {@link CourierRecord #userId}
     *      *               <br> - {@link CourierRecord #markQuality}
     *      *               <br> - {@link CourierRecord #markPoliteness}
     *      *               <br> - {@link CourierRecord #markPunctuality}
     *      *               <br> - {@link CourierRecord #status}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    @Override
    public abstract CourierRecord insert(CourierRecord courierRecord) throws DAOException;

    /**
     * Get courierRecord  from database
     *
     * @param courierId - id of courier.
     *     The method gets only:
     *      *               <br> - {@link CourierRecord #recordId}
     *      *               <br> - {@link CourierRecord #courierLogin}
     *      *               <br> - {@link CourierRecord #markQuality}
     *      *               <br> - {@link CourierRecord #markPoliteness}
     *      *               <br> - {@link CourierRecord #markPunctuality}
     *      *               <br> - {@link CourierRecord #markCommon}
     *      *               <br> - {@link CourierRecord #status}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CourierRecord get(int courierId) throws DAOException;

    /**
     * Get courierRecord  from database
     *
     * @param courierRecord where must be set following properties:
//     *      *               <br> - {@link CourierRecord #userId}
//     *      *               <br> - {@link CourierRecord #courierLogin}
     *      *               <br> - {@link CourierRecord #markQuality}
     *      *               <br> - {@link CourierRecord #markPoliteness}
     *      *               <br> - {@link CourierRecord #markPunctuality}
     *      *               <br> - {@link CourierRecord #markCommon}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CourierRecord get(CourierRecord courierRecord) throws DAOException;
    /**
     * Get list of courier's records.
     *
     * This method get only:
     *      *               <br> - {@link CourierRecord #courierId}
     *      *               <br> - {@link User #name}
     *      *               <br> - {@link User #mark}
     *
     * @return  listCourierRecords - list of courier's records
     * @throws DAOException if something went wrong
     */
    public abstract List<CourierRecord> getAllCouriersRecords() throws DAOException;

    /**
     * Get list of new courier's records.
     *
     * This method get only:
     *      *               <br> - {@link CourierRecord #courierId}
     *      *               <br> - {@link User #name}
     *      *               <br> - {@link User #mark}
     *
     * @return  listCourierRecords - list of courier's records
     * @throws DAOException if something went wrong
     */
    public abstract List<CourierRecord> getNewCouriersRecords() throws DAOException;

    /**
     * Get list of id of orders which courier should does
     *
     * @return  allOrderId - list of id of orders
     * @throws DAOException if something went wrong
     */
    public abstract List<Integer> getAllOrderId(int courierId) throws DAOException;

    /**
     * Set active status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void acceptCourier(int courierRecordId)throws DAOException;

    /**
     * Set not available status to courier
     *
     * @param courierRecordId - courier courier record id
     *
     * @throws DAOException if something went wrong
     */
    public abstract void rejectCourier(int courierRecordId)throws DAOException;

    @Override
    public void delete(int id) throws DAOException {

    }
}
