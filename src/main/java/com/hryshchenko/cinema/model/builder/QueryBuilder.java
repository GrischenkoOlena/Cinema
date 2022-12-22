package com.hryshchenko.cinema.model.builder;

import com.hryshchenko.cinema.model.connectionpool.DBManager;
import com.hryshchenko.cinema.model.entity.Entity;

import java.sql.*;
import java.util.List;

public abstract class QueryBuilder <T extends Entity> {

    public abstract T getResult (ResultSet rs) throws SQLException;
    public abstract List<T> getListOfResult (ResultSet rs) throws SQLException;

    public final boolean execute(final DBManager dbManager, String query, Object... params) throws SQLException{
        boolean res;
        Connection conn = dbManager.getConnection();
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            int rows = prepareStatement.executeUpdate();
            res = rows > 0;
        }
        dbManager.closeConnection(conn);
        return res;
    }
    public final T executeAndReturnValue(final DBManager dbManager, String query, Object... params) throws SQLException{
        T value;
        Connection conn = dbManager.getConnection();
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            value = getResult(resultSet);
        }
        dbManager.closeConnection(conn);
        return value;
    }

    public final List<T> executeAndReturnList(final DBManager dbManager, String query, Object... params) throws SQLException{
        List<T> values;
        Connection conn = dbManager.getConnection();
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            values = getListOfResult(resultSet);
            }
        dbManager.closeConnection(conn);
        return values;
    }

    private void insertSQLParams(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

}
