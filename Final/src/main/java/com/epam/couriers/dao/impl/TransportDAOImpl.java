package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.TransportDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.entity.TransportEnum;
import com.epam.couriers.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportDAOImpl extends TransportDAO {
    private static final String QUERY_ADD_NEW_COURIER_TRANSPORT = "INSERT INTO transport (typeTransport, courierRecordId) " +
            "VALUES (?,?);";

    private static final String SQL_GET_TRANSPORT_INF_OF_ONE_COURIER = "SELECT t.typeTransport from Transport t " +
            "WHERE t.courierRecordId = ?";


    private static final String SQL_GET_TRANSPORT_INF_WITH_LIMIT = "SELECT t.typeTransport, u.login from Transport t " +
            "INNER JOIN CourierRecord cr ON t.courierRecordId = cr.courierRecordId " +
            "INNER JOIN User u ON cr.userId = u.userId  WHERE cr.status = '1' OR cr.status = '3' ORDER BY t.typeTransport LIMIT ?,?;;";

    private static final String SQL_GET_COUNT_OF_TRANSPORT = "SELECT COUNT(transportId) " +
            "FROM transport ORDER BY transportId;";

    private static final String SQL_DELETE_TRANSPORT_ONE_CUSTOMER = "DELETE FROM transport WHERE courierRecordId = ?";



    @Override
    public void insert(int courierRecordId, String transport) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_COURIER_TRANSPORT)) {
            preparedStatement.setString(1,transport);
            preparedStatement.setInt(2, courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating courierRecord failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Transport> getTransportsOfOneCourier(int courierRecordId) throws DAOException {
        List<Transport> listCourierTransports = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TRANSPORT_INF_OF_ONE_COURIER )) {
            preparedStatement.setInt(1,courierRecordId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Transport transport = new Transport();
                String type = TransportEnum.toEnumFormat(rs.getString(GeneralConstant.TYPE_TRANSPORT));
                transport.setTypeTransport(TransportEnum.valueOf(type));
                transport.setCourier(new User(courierRecordId));
                listCourierTransports.add(transport);
            }
            return listCourierTransports;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Override
    public List<Transport> findWithLimitTransport(int startIndex, int countOfTransportOnOnePage) throws DAOException {
        List<Transport> listCourierTransports = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TRANSPORT_INF_WITH_LIMIT)) {
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, countOfTransportOnOnePage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Transport transport = new Transport();
                String type = TransportEnum.toEnumFormat(rs.getString(GeneralConstant.TYPE_TRANSPORT));
                transport.setTypeTransport(TransportEnum.valueOf(type));
                transport.setCourier(new User(rs.getString(GeneralConstant.USER_LOGIN)));
                listCourierTransports.add(transport);
            }
            return listCourierTransports;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int findTotalCountOfTransport() throws DAOException {
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_TRANSPORT)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int courierRecordId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TRANSPORT_ONE_CUSTOMER)) {
            preparedStatement.setInt(1, courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Deleting transport of courier '"+ courierRecordId + "'  failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
