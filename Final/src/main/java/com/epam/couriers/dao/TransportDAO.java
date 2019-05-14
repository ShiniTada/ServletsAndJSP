package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.entity.User;

import java.util.List;

public  abstract class TransportDAO extends BaseDAO<Transport>{
    /**
     * Inserts transport into database
     *
     * @param courierRecordId - id of owner
     * @param  transport - type transport
     * @throws DAOException if something went wrong
     */
    public  abstract void insert(int courierRecordId, String transport) throws DAOException;

    /**
     * Get list of type transport which use one courier.
     *
     * This method get only:
     *      *               <br> - {@link Transport #typeTransport}
     *      *               <br> - {@link User #name}
     *
     * @return  listCourierTransports -  list of courier transport
     * @throws DAOException if something went wrong
     */
    public  abstract List<Transport> getTransportsOfOneCourier(int courierRecordId) throws DAOException;

    /**
     * Get list of type transport which use all couriers.
     *
     * This method get only:
     *      *               <br> - {@link Transport #typeTransport}
     *      *               <br> - {@link User #name}
     *
     * @return  listCourierTransports -  list of courier's transport
     * @throws DAOException if something went wrong
     */
    public abstract List<Transport> getCourierTransports() throws DAOException;

    @Override
    public Transport insert(Transport entity) throws DAOException {
        return null;
    }
    @Override
    public void delete(int id) throws DAOException {
    }
}
