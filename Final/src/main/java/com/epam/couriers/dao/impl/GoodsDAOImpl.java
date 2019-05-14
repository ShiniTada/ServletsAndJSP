package com.epam.couriers.dao.impl;

import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.dao.GoodsDAO;
import com.epam.couriers.dao.exception.DAOException;
import com.epam.couriers.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl extends GoodsDAO {
    private static final String QUERY_ADD_NEW_COURIER_GOODS = "INSERT INTO goods (typeGoods, courierRecordId) VALUES (?,?);";

    private static final String SQL_GET_GOODS_INF = "SELECT g.typeGoods, u.login from Goods g " +
            "INNER JOIN CourierRecord cr ON g.courierRecordId = cr.courierRecordId " +
            "INNER JOIN User u ON cr.userId = u.userId WHERE cr.status = '1' ORDER BY g.typeGoods;";

    private static final String SQL_GET_GOODS_INF_OF_ONE_COURIER = "SELECT g.typeGoods from Goods g WHERE g.courierRecordId = ?";

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
    public List<Goods> getCourierGoods() throws DAOException {
        List<Goods> listCourierGoods = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_GOODS_INF)) {
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

}
