package com.epam.couriers.dao;


import com.epam.couriers.dao.connectionpool.ProxyConnection;
import com.epam.couriers.entity.Entity;
import com.epam.couriers.dao.exception.DAOException;

/**
 * Base interface for all DAO's
 */
public abstract class BaseDAO<E extends Entity> {

    protected ProxyConnection connection;
    /**
     * Inserts new answer into database
     *
     * @param entity with some initialized properties
     * @return {@link Entity} entity with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract  E insert(E entity) throws DAOException;

    /**
     * Removes entity with the specified id
     *
     * @param id id of a question to delete
     * @throws DAOException if something went wrong
     */
    public abstract void delete(int id) throws DAOException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }
}
