package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.model.connectionpool.DBManager;
import com.hryshchenko.cinema.model.dao.AbstractDAO;
import com.hryshchenko.cinema.model.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

    private final static DBManager dbManager = DBManager.getInstance();
    private Connection connection;

    @SafeVarargs
    public final void initTransaction(AbstractDAO dao, AbstractDAO... daos) {
        if (connection == null) {
            connection = dbManager.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.setConnection(connection);

        for (AbstractDAO daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }
    public void endTransaction() {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
