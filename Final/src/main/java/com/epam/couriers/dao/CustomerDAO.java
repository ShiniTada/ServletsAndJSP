package com.epam.couriers.dao;

import com.epam.couriers.dao.BaseDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.CustomerOrder;

public abstract class CustomerDAO extends BaseDAO<CustomerOrder> {

    /**
     * Get courierRecord  from database
     *
     * @param orderId - order id
     * This method get only:
     * where must be set following properties:
     *      *               <br> - {@link CustomerOrder #from}
     *      *               <br> - {@link CustomerOrder #to}
     *      *               <br> - {@link CustomerOrder #introductionDate}
     *      *               <br> - {@link CustomerOrder #goodDescriprion}
     *      *               <br> - {@link CustomerOrder #customerId}
     *      *               <br> - {@link CustomerOrder #status}
     * @return {@link CustomerOrder } with all filled properties
     * @throws DAOException if something went wrong
     */
    public abstract CustomerOrder get(int orderId) throws DAOException;

    @Override
    public CustomerOrder insert(CustomerOrder entity) throws DAOException {
        return null;
    }

    @Override
    public void delete(int id) throws DAOException {

    }
}
