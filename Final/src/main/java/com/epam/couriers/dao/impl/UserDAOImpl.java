package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.UserDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    private static final String SQL_GET_ALL_USERS_NAMES = "SELECT userId, login FROM user;";

    private static final String SQL_GET_USER = "SELECT userId, role FROM user WHERE login= ? AND hashPassword= ?;";

    private static final String SQL_ADD_NEW_USER = "INSERT INTO user (login, hashPassword,  role) VALUES (?,?,?);";

    private static final String SQL_CHANGE_USER_PASSWORD = "UPDATE user SET hashPassword= ? WHERE userId= ?;";


    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_USERS_NAMES)) {
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
     *
     * @param user - user
     * @return <code>User</code>
     * @throws DAOException if something went wrong
     */
    @Override
    public User authorization(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            String role = null;
            while (rs.next()) {
                user.setId(rs.getInt(GeneralConstant.USER_ID));
                role = RoleEnum.toEnumFormat(rs.getString(GeneralConstant.USER_ROLE));
                user.setRole(RoleEnum.valueOf(role));
            }
            if (role != null) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getValue());
            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.warn("Creating user failed, no rows affected.");
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            } else {
                LOGGER.warn("Creating user failed, no ID obtained.");
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_PASSWORD)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());
            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.warn("Updating user failed, no rows affected.");
                throw new SQLException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
