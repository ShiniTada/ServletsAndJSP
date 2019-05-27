package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;

import java.util.List;

public abstract class TransportDAO extends BaseDAO<Transport> {
    /**
     * Inserts transport into database
     *
     * @param courierRecordId - id of owner
     * @param transport       - type transport
     * @throws DAOException if something went wrong
     */
    public abstract void insert(int courierRecordId, String transport) throws DAOException;

    /**
     * Get list of type transport which use one courier.
     * <p>
     * This method get only:
     * *               <br> - {@link Transport #typeTransport}
     * *               <br> - {@link User #name}
     *
     * @return listCourierTransports -  list of courier transport
     * @throws DAOException if something went wrong
     */
    public abstract List<Transport> getTransportsOfOneCourier(int courierRecordId) throws DAOException;

    /**
     * Get list of type transport which use all couriers.
     * <p>
     * This method get only:
     * *               <br> - {@link Transport #typeTransport}
     * *               <br> - {@link User #name}
     *
     * @return listCourierTransports -  list of courier's transport
     * @throws DAOException if something went wrong
     */
    public abstract List<Transport> findWithLimitTransport(int startIndex, int countOfTransportOnOnePage) throws DAOException;


    /**
     * @return count of transport
     * @throws ServiceException if error happens during execution
     */
    public abstract int findTotalCountOfTransport() throws ServiceException, DAOException;

    /**
     * @param courierRecordId - courier record id
     * @throws DAOException if something went wrong
     */
    @Override
    public abstract void delete(int courierRecordId) throws DAOException;

    @Override
    public Transport insert(Transport entity) {
        return null;
    }
}
