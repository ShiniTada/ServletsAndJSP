package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.GoodsDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;
import com.epam.couriers.service.exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl extends GoodsDAO {
    private static final String QUERY_ADD_NEW_COURIER_GOODS = "INSERT INTO goods (typeGoods, courierRecordId) VALUES (?,?);";

    private static final String SQL_GET_GOODS_INF_WITH_LIMIT = "SELECT g.typeGoods, u.login FROM Goods g " +
            "INNER JOIN CourierRecord cr ON g.courierRecordId = cr.courierRecordId " +
            "INNER JOIN User u ON cr.userId = u.userId  WHERE (cr.status = '1' OR cr.status = '3') ORDER BY g.typeGoods LIMIT ?, ?;";

    private static final String SQL_GET_COUNT_OF_GOODS = "SELECT COUNT(goodsId) " +
            "FROM goods ORDER BY goodsId;";

    private static final String SQL_GET_GOODS_INF_OF_ONE_COURIER = "SELECT g.typeGoods from Goods g WHERE g.courierRecordId = ?";

    private static final String SQL_DELETE_GOODS_ONE_CUSTOMER = "DELETE FROM goods WHERE courierRecordId = ?";


    @Override
    public void insert(int courierRecordId, String goods) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_NEW_COURIER_GOODS)) {
            preparedStatement.setString(1, goods);
            preparedStatement.setInt(2, courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Creating courierRecord failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Goods> getGoodsOfOneCourier(int courierRecordId) throws DAOException {
        List<Goods> listCourierGoods = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_GOODS_INF_OF_ONE_COURIER)) {
            preparedStatement.setInt(1,courierRecordId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                String type = TransportEnum.toEnumFormat(rs.getString(GeneralConstant.TYPE_GOODS));
                goods.setTypeGoods(GoodsEnum.valueOf(type));
                goods.setCourier(new User(courierRecordId));
                listCourierGoods.add(goods);
            }
            return listCourierGoods;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Goods> findWithLimitGoods(int startIndex, int countOfGoodsOnOnePage) throws DAOException {
        List<Goods> listCourierGoods = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_GOODS_INF_WITH_LIMIT)) {
            preparedStatement.setInt(1, startIndex);
            preparedStatement.setInt(2, countOfGoodsOnOnePage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                String type = GoodsEnum.toEnumFormat(rs.getString(GeneralConstant.TYPE_GOODS));
                goods.setTypeGoods(GoodsEnum.valueOf(type));
                goods.setCourier(new User(rs.getString(GeneralConstant.USER_LOGIN)));
                listCourierGoods.add(goods);
            }
            return listCourierGoods;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int findTotalCountOfGoods() throws DAOException {
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_GOODS)) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_GOODS_ONE_CUSTOMER)) {
            preparedStatement.setInt(1, courierRecordId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Deleting goods of courier '"+ courierRecordId + "'  failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
