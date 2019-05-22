package com.epam.couriers.dao;

import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.User;

import java.util.List;

public abstract class UserDAO extends BaseDAO<User>{


    /**
     * Get list of users
     *
     * This method get only:
     *      *               <br> - {@link User #id}
     *      *               <br> - {@link User #login}
     *      *               <br> - {@link User #password}
     *      *               <br> - {@link User #role}
     * @return  list of users
     * @throws DAOException if something went wrong
     */
    public abstract List<User> getAllUsers() throws DAOException;

    /**
     * Check if there is a user in the database. If he is, give all the information about him.
     * @param user - user
     * @return <code>User</code>
     * @throws DAOException if something went wrong
     */
    public abstract User authorization(User user) throws DAOException;


    /**
     * Inserts user into database
     *
     * @param user where must be set following properties:
     *      *               <br> - {@link User #name} of new user
     *      *               <br> - {@link User #login} of new user
     *      *               <br> - {@link User #password} of new user
     *      *               <br> - {@link User #role} of new user
     * @return {@link User} with all filled properties
     * @throws DAOException if something went wrong
     */
    @Override
    public abstract User insert(User user) throws DAOException;


    @Override
    public void delete(int userId) {}

    /**
     * @param user where must be set following properties:
     *      *               <br> - {@link User #id} of user
     *      *               <br> - {@link User #password} - new user password
     * @throws DAOException if something went wrong
     */
    public abstract void update(User user)throws DAOException;
}

