package com.epam.couriers.dao;


import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.User;

import java.util.List;

public abstract class CourierDAO extends BaseDAO<CourierRecord> {
    /**
     * Inserts courierRecord into database
     *
     * @param courierRecord where must be set following properties:
     *                      *               <br> - {@link CourierRecord #userId}
     *                      *               <br> - {@link CourierRecord #markQuality}
     *                      *               <br> - {@link CourierRecord #markPoliteness}
     *                      *               <br> - {@link CourierRecord #markPunctuality}
     *                      *               <br> - {@link CourierRecord #status}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    @Override
    public abstract CourierRecord insert(CourierRecord courierRecord) throws DAOException;

    /**
     * Get courierRecord from database
     *
     * @param courierId - id of courier.
     *                  The method gets only:
     *                  *               <br> - {@link CourierRecord #recordId}
     *                  *               <br> - {@link CourierRecord #courierLogin}
     *                  *               <br> - {@link CourierRecord #markQuality}
     *                  *               <br> - {@link CourierRecord #markPoliteness}
     *                  *               <br> - {@link CourierRecord #markPunctuality}
     *                  *               <br> - {@link CourierRecord #markCommon}
     *                  *               <br> - {@link CourierRecord #status}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CourierRecord get(int courierId) throws DAOException;

    /**
     * @param courierLogin - courier login
     *                     The method gets only:
     *                     *      *               <br> - {@link CourierRecord #recordId}
     *                     *      *               <br> - {@link CourierRecord #markQuality}
     *                     *      *               <br> - {@link CourierRecord #markPoliteness}
     *                     *      *               <br> - {@link CourierRecord #markPunctuality}
     *                     *      *               <br> - {@link CourierRecord #markCommon}
     *                     *      *               <br> - {@link CourierRecord #votesNumber}
     * @return {@link CourierRecord } with filled marks and id of record
     * @throws DAOException if something went wrong
     */
    public abstract CourierRecord getMarksAndIdOfOneCourier(String courierLogin) throws DAOException;

    /**
     * The method updates:
     * *               <br> - {@link CourierRecord #markQuality}
     * *               <br> - {@link CourierRecord #markPoliteness}
     * *               <br> - {@link CourierRecord #markPunctuality}
     * *               <br> - {@link CourierRecord #markCommon}
     * *               <br> - {@link CourierRecord #votesNumber}
     *
     * @throws DAOException if something went wrong
     */
    public abstract void updateMarks(int courierRecordId, double newQuality, double newPoliteness, double newPunctuality, double newCommon, int newVotesNumber) throws DAOException;

    /**
     * Get courierRecord  from database
     * The method gets courier record from database with filled:
     * *               <br> - {@link CourierRecord #userId}
     * *               <br> - {@link CourierRecord #courierLogin}
     * *               <br> - {@link CourierRecord #markQuality}
     * *               <br> - {@link CourierRecord #markPoliteness}
     * *               <br> - {@link CourierRecord #markPunctuality}
     * *               <br> - {@link CourierRecord #markCommon}
     * *               <br> - {@link CourierRecord #status}
     *
     * @param courierRecord where must be set following properties:
     *                      *               <br> - {@link CourierRecord #userId}
     * @return {@link CourierRecord } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CourierRecord get(CourierRecord courierRecord) throws DAOException;

    /**
     * Get {@code countOfCouriersOnOnePage} courier's records with active
     * and blocked status, which started with {@code startIndex}.
     * <p>
     * This method get only:
     * *               <br> - {@link CourierRecord #courierId}
     * *               <br> - {@link User #name}
     * *               <br> - {@link User #mark}
     *
     * @return listCourierRecords - list of courier's records
     * @throws DAOException if something went wrong
     */
    public abstract List<CourierRecord> findWithLimitCouriersRecords(int startIndex, int countOfCouriersOnOnePage) throws DAOException;

    /**
     * Get {@code countOfCouriersOnOnePage} courier's records with active
     * status, which started with {@code startIndex}.
     * <p>
     * This method get only:
     * *               <br> - {@link CourierRecord #courierId}
     * *               <br> - {@link User #name}
     * *               <br> - {@link User #mark}
     *
     * @return listCourierRecords - list of courier's records
     * @throws DAOException if something went wrong
     */
    public abstract List<CourierRecord> getCouriersRecordsForCustomer() throws DAOException;

    /**
     * @return count of courier records with active and blocked status
     * @throws DAOException if something went wrong
     */
    public abstract int findTotalCountOfCourierRecords() throws DAOException;

    /**
     * Get list of new courier's records.
     * <p>
     * This method get only:
     * *               <br> - {@link CourierRecord #courierId}
     * *               <br> - {@link User #name}
     * *               <br> - {@link User #mark}
     *
     * @return listCourierRecords - list of courier's records
     * @throws DAOException if something went wrong
     */
    public abstract List<CourierRecord> getNewCouriersRecords() throws DAOException;

    /**
     * Set active status to courier
     *
     * @param courierRecordId - courier record id
     * @throws DAOException if something went wrong
     */
    public abstract void acceptCourier(int courierRecordId) throws DAOException;

    /**
     * Set not available status to courier
     *
     * @param courierRecordId - courier record id
     * @throws DAOException if something went wrong
     */
    public abstract void rejectCourier(int courierRecordId) throws DAOException;

    /**
     * Set blocked status to courier
     *
     * @param courierRecordId - courier record id
     * @throws DAOException if something went wrong
     */
    public abstract void blockCourier(int courierRecordId) throws DAOException;

    @Override
    public void delete(int id) {
    }
}
