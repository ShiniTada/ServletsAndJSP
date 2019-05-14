//package com.epam.couriers.dao.connectionpool;
//
//import com.epam.couriers.dao.exception.ConnectionPoolException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.testng.Assert.*;
//
//public class ConnectionPoolTest {
//
//    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolTest.class);
//    private static ConnectionPool poolToTest = ConnectionPool.getInstance();
//
//    @Test
//    public void testGetAndCloseConnectionTest() {
//        try {
//            Connection connection = poolToTest.getConnection();
//            connection.close();
//        } catch (ConnectionPoolException e) {
//            LOGGER.error("Cannot get connection from pool\n", e);
//            fail("Cannot get connection from pool");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @AfterTest
//    public static void tearDown() {
//        poolToTest.releasePool();
//    }
//}