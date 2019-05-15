package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import com.epam.couriers.dao.exception.DAOException;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    private static final String QUERY_GET_ALL_USERS_NAMES = "SELECT userId, login FROM user;";
    private static final String QUERY_GET_USER = "SELECT userId, login, role FROM user WHERE login= ? AND hashPassword= ?;";
    private static final String QUERY_FIND_USER_BY_LOGIN = "SELECT userId FROM user WHERE login= ?;";
    private static final String QUERY_ADD_NEW_USER = "INSERT INTO user (login, hashPassword,  role) VALUES (?,?,?);";
    private static final String QUERY_UPDATE_USER = " UPDATE user SET login= ?, login= ? ,hashPassword= ? WHERE userId= ?;";
    private static final String QUERY_DELETE_USER = "DELETE FROM user WHERE userId = ?;";

    /**
     * ////////////////////////////////////////////////////////////////////////////////////////////////////
     * NULL POINER
     * @return
     * @throws DAOException
     */

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_ALL_USERS_NAMES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(GeneralConstant.USER_ID));
                user.setLogin(rs.getString(GeneralConstant.USER_LOGIN));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Check if there is a user in the database. If he is, give all the information about him.
     * @param user
     * @return <code>User</code>
     * @throws DAOException if something went wrong
     */
    @Override
    public User authorization(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            String role = null;
            while (rs.next()) {
                user.setId(rs.getInt(GeneralConstant.USER_ID));
//                user.setLogin(rs.getString(NAME));
                role = RoleEnum.toEnumFormat(rs.getString(GeneralConstant.USER_ROLE));
                user.setRole(RoleEnum.valueOf(role));
            }
            if(role != null) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e);

        }
    }

    @Override
    public User insert(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getValue());
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }


    @Override
    public void delete(int userId) throws DAOException {

    }

//        @Override
//    public User insert(User user) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(QUERY_FIND_USER_BY_LOGIN);
//            preparedStatement.setString(1, user.getLogin());
//            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                return null;
//            }
//
//            preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, user.getLogin());
//            preparedStatement.setString(2, user.getLogin());
//            preparedStatement.setString(3, user.getPassword());
//
//            preparedStatement.setString(4, user.getRole().getValue());
//            if (preparedStatement.executeUpdate() == 0) {
//                throw new SQLException("Creating user failed, no rows affected.");
//            }
//            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
//                if (keys.next()) {
//                    user.setId(keys.getInt(1));
//                } else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
//            return user;
//
//
//        } catch (ConnectionPoolException | SQLException e) {
//
//            throw new DAOException(e);
//
//        } finally {
////            connectionPool.closeConnection(connection, preparedStatement, rs);
//        }
//    }

//    @Override
//    public void update(User user) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(QUERY_UPDATE_USER);
//            preparedStatement.setString(1, user.getLogin());
//            preparedStatement.setString(2, user.getLogin());
//            preparedStatement.setString(3, user.getPassword());
//            preparedStatement.setInt(4, user.getId());
//            preparedStatement.executeUpdate();
//
//        } catch (ConnectionPoolException | SQLException e) {
//
//            throw new DAOException(e);
//
//        } finally {
//            connectionPool.closeConnection(connection, preparedStatement);
//        }
//    }
//
//        @Override
//        public void delete (int userId) throws DAOException {
//            ConnectionPool connectionPool = ConnectionPool.getInstance();
//            Connection connection = null;
//            PreparedStatement preparedStatement = null;
//            try {
//                connection = connectionPool.getConnection();
//                preparedStatement = connection.prepareStatement(QUERY_DELETE_USER );
//                preparedStatement.setInt(1, userId);
//                preparedStatement.executeUpdate();
//            } catch (ConnectionPoolException | SQLException e) {
//
//                throw new DAOException(e);
//
//            } finally {
//                connectionPool.closeConnection(connection, preparedStatement);
//            }
//         }

}
