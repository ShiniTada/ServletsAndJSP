package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.entity.User;

import java.util.List;

public abstract class GoodsDAO extends BaseDAO<Goods> {
    /**
     * Inserts goods into database
     *
     * @param courierRecordId - id of owner
     * @param  goods - type goods
     * @throws DAOException if something went wrong
     */
    public  abstract void insert(int courierRecordId, String goods) throws DAOException;

    /**
     * Get list of type goods which use one courier.
     *
     * This method get only:
     *      *               <br> - {@link Goods #typeTransport}
     *      *               <br> - {@link User #name}
     *
     * @return  listCourierGoods -  list of courier goods
     * @throws DAOException if something went wrong
     */
    public  abstract List<Goods> getGoodsOfOneCourier(int courierRecordId) throws DAOException;
    /**
     * Get list of type goods which couriers deliver.
     *
     * This method get only:
     *      *               <br> - {@link Goods #typeGoods}
     *      *               <br> - {@link User #name}
     *
     * @return  listCourierTransports -  list of courier's transports
     * @throws DAOException if something went wrong
     */
    public abstract List<Goods> getCourierGoods() throws DAOException;

    @Override
    public Goods insert(Goods entity) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {

    }
}
