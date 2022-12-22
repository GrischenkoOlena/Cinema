package com.hryshchenko.cinema.model.connectionpool;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DBManager {
    private static DBManager instance;
    private ComboPooledDataSource connectionPool;

    public static synchronized DBManager getInstance(){
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){
        try {
            createPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(DBManager.class.getResourceAsStream("/database.properties"));
        return props;
    }

    private void createPool() throws IOException, PropertyVetoException {
        Properties props = getProperties();
        connectionPool = new ComboPooledDataSource();
        connectionPool.setDriverClass(props.getProperty("db.driver"));
        connectionPool.setJdbcUrl(props.getProperty("db.url"));
        connectionPool.setUser(props.getProperty("user"));
        connectionPool.setPassword(props.getProperty("password"));
        connectionPool.setMaxPoolSize(Integer.parseInt(props.getProperty("poolsize")));
    }


}
