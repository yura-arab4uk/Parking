package com.parking.model.business.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;

/**
 * Created by Yurii on 17.01.2017.
 */
public class MyDataSource {
    private static DataSource ds;
    static {
        try {
            InitialContext initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc/parking");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        Connection connection = ds.getConnection();
        return connection;
    }

}
