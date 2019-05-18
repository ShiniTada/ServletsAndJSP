package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.CourierDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourierDAOImpl extends CourierDAO {

    private static final String QUERY_ADD_NEW_COURIER_RECORD = "INSERT INTO courierrecord (userId, markQuality, markPoliteness, markPunctuality, " +
            "markCommon, status) VALUES (?,?,?,?,?,?);";
    private static final String SQL_GET_COURIER_RECORD_INF = "SELECT courierRecordId, status, markQuality, markPoliteness, markPunctuality," +
            " markCommon FROM courierRecord WHERE userId= ?";

    private static final String SQL_GET_COURIER_INF = "SELECT cr.courierRecordId, u.login, cr.markCommon " +
            "FROM courierRecord cr INNER JOIN User u ON cr.userId = u.userId  WHERE cr.status = '1';";


    private static final String SQL_GET_NEW_COURIER_INF = "SELECT cr.courierRecordId, u.login " +
            "FROM courierRecord cr INNER JOIN User u ON cr.userId = u.userId  WHERE cr.status = '0';";

    private static final String SQL_GET_COURIER_RECORD_INF_BY_ID = "SELECT cr.userId, u.login, cr.markCommon, " +
            "cr.markQuality, cr.markPoliteness, cr.markPunctuality, cr.status FROM courierRecord cr " +
            "INNER JOIN User u ON cr.userId = u.userId  WHERE cr.courierRecordId = ?";

    private static final String SQL_GET_MARKS_AND_ID_BY_COURIER_LOGIN = "SELECT cr.courierRecordId, cr.markCommon, " +
            "cr.markQuality, cr.markPoliteness, cr.markPunctuality, cr.votesNumber FROM courierRecord cr " +
            "INNER JOIN User u ON cr.userId = u.userId  WHERE u.login = ?";

    private static final String SQL_GET_ORDERS_ID = "SELECT orderId from courier_has_customerorder WHERE courierId = ?";

    private static final String SQL_UPDATE_MARKS_AND_VOTES_NUMBER = "UPDATE courierrecord SET markQuality= ?, markPoliteness= ?, markPunctuality= ?," +
            " markCommon= ?, votesNumber=? WHERE courierRecordId = ?";

    private static final String QUERY_UPDATE_COURIER_RECORD_STATUS = "UPDATE courierrecord SET status= ? WHERE courierRecordId = ?";


    @Override
    public CourierRecord insert(CourierRecord courierRecord) throws DAOException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_COURIER_RECORD, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, courierRecord.getCourier().getId());
                preparedStatement.setDouble(2, courierRecord.getMarkQuality());
                preparedStatement.setDouble(3, courierRecord.getMarkPoliteness());
                preparedStatement.setDouble(4, courierRecord.getMarkPunctuality());
                preparedStatement.setDouble(5, courierRecord.getMarkCommon());
                preparedStatement.setInt(6, courierRecord.getStatus());
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException("Creating courierRecord failed, no rows affected.");
                }
                ResultSet keys = preparedStatement.getGeneratedKeys();
                if (keys.next()) {
                    courierRecord.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Creating courierRecord failed, no ID obtained.");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return courierRecord;
        }

    @Override
    public CourierRecord get(int courierId) throws DAOException {
        CourierRecord courierRecord = new CourierRecord();
        courierRecord.setCourier(new User(courierId));
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COURIER_RECORD_INF)) {
            preparedStatement.setInt(1, courierRecord.getCourier().getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                courierRecord.setId(rs.getInt(GeneralConstant.COURIER_RECORD_ID));
                courierRecord.setStatus(rs.getInt(GeneralConstant.STATUS));
                courierRecord.setMarkQuality(rs.getDouble(GeneralConstant.MARK_QUALITY));
                courierRecord.setMarkPoliteness(rs.getDouble(GeneralConstant.MARK_POLITENESS));
                courierRecord.setMarkPunctuality(rs.getDouble(GeneralConstant.MARK_PUNCTUALITY));
                courierRecord.setMarkCommon(rs.getDouble(GeneralConstant.MARK_COMMON));
            }
            return courierRecord;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public CourierRecord getMarksAndIdOfOneCourier(String courierLogin) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MARKS_AND_ID_BY_COURIER_LOGIN)) {
            preparedStatement.setString(1, courierLogin);
            ResultSet rs = preparedStatement.executeQuery();
            CourierRecord courierRecord = new CourierRecord();
            while (rs.next()) {
                courierRecord.setId(rs.getInt(GeneralConstant.COURIER_RECORD_ID));
                courierRecord.setMarkQuality(rs.getDouble(GeneralConstant.MARK_QUALITY));
                courierRecord.setMarkPoliteness(rs.getDouble(GeneralConstant.MARK_POLITENESS));
                courierRecord.setMarkPunctuality(rs.getDouble(GeneralConstant.MARK_PUNCTUALITY));
                courierRecord.setMarkCommon(rs.getDouble(GeneralConstant.MARK_COMMON));
                courierRecord.setVotes(rs.getInt(GeneralConstant.VOTES_NUMBER));
            }
            return courierRecord;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    //    private static final String SQL_UPDATE_MARKS_AND_VOTES_NUMBER = "UPDATE courierrecord SET markQuality= ?, markPoliteness= ?, markPunctuality= ?," +
    //            " markCommon= ?, votesNumber=? WHERE courierRecordId = ?";
    //

    @Override
    public void updateMarks(int courierRecordId, double newQuality, double newPoliteness, double newPunctuality, double newCommon, int newVotesNumber) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MARKS_AND_VOTES_NUMBER)) {
            preparedStatement.setDouble(1, newQuality);
            preparedStatement.setDouble(2, newPoliteness);
            preparedStatement.setDouble(3, newPunctuality);
            preparedStatement.setDouble(4, newCommon);
            preparedStatement.setInt(5,  newVotesNumber);
            preparedStatement.setInt(6,  courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Updating marks failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public CourierRecord get(CourierRecord courierRecord) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COURIER_RECORD_INF_BY_ID)) {
            preparedStatement.setInt(1, courierRecord.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                courierRecord.setCourier(new User(rs.getInt(GeneralConstant.USER_ID), rs.getString(GeneralConstant.USER_LOGIN)));
                courierRecord.setMarkQuality(rs.getDouble(GeneralConstant.MARK_QUALITY));
                courierRecord.setMarkPoliteness(rs.getDouble(GeneralConstant.MARK_POLITENESS));
                courierRecord.setMarkPunctuality(rs.getDouble(GeneralConstant.MARK_PUNCTUALITY));
                courierRecord.setMarkCommon(rs.getDouble(GeneralConstant.MARK_COMMON));
                courierRecord.setStatus(rs.getInt(GeneralConstant.STATUS));
            }
            return courierRecord;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    @Override
    public List<CourierRecord> getAllCouriersRecords() throws DAOException {
        List<CourierRecord> listCourierRecords = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COURIER_INF)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CourierRecord courierRecord = new CourierRecord();
                courierRecord.setId(rs.getInt(GeneralConstant.COURIER_RECORD_ID));
                courierRecord.setCourier(new User(rs.getString(GeneralConstant.USER_LOGIN)));
                courierRecord.setMarkCommon(rs.getDouble(GeneralConstant.MARK_COMMON));
                courierRecord.setStatus(1);
                listCourierRecords.add(courierRecord);
            }
            return listCourierRecords;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<CourierRecord> getNewCouriersRecords() throws DAOException {
        List<CourierRecord> listNewCourierRecords = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEW_COURIER_INF)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CourierRecord newCourierRecord = new CourierRecord();
                newCourierRecord.setId(rs.getInt(GeneralConstant.COURIER_RECORD_ID));
                newCourierRecord.setCourier(new User(rs.getString(GeneralConstant.USER_LOGIN)));
                newCourierRecord.setStatus(0);
                listNewCourierRecords.add(newCourierRecord);
            }
            return listNewCourierRecords;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Integer> getAllOrderId(int courierId) throws DAOException {
        List<Integer> allOrderId = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ORDERS_ID)) {
            preparedStatement.setInt(1, courierId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOrderId.add(rs.getInt(GeneralConstant.ORDER_ID));
            }
            return allOrderId;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void acceptCourier(int courierRecordId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_COURIER_RECORD_STATUS)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2,courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating bundle failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void rejectCourier(int courierRecordId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_COURIER_RECORD_STATUS)) {
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2,courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating bundle failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
