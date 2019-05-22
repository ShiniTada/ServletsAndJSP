package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.exception.ServiceException;

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
    public abstract List<Goods> findWithLimitGoods(int startIndex, int countOfGoodsOnOnePage) throws DAOException;

    /**
     * @return count of goods
     * @throws ServiceException if error happens during execution
     */
    public abstract int findTotalCountOfGoods()throws ServiceException, DAOException;

    /**
     * @param courierRecordId - courier record id
     * @throws DAOException  if something went wrong
     */
    @Override
    public abstract void delete(int courierRecordId) throws DAOException;

    @Override
    public Goods insert(Goods entity){
        return null;
    }
}
