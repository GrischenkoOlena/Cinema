package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Entity;

import java.sql.*;
import java.util.List;

public abstract class QueryExecutor<T extends Entity> {

    public abstract T getResult (ResultSet rs) throws SQLException;
    public abstract List<T> getListOfResult (ResultSet rs) throws SQLException;

    public final boolean execute(final Connection conn, String query, Object... params) throws SQLException{
        boolean res;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            int rows = prepareStatement.executeUpdate();
            res = rows > 0;
        }
        return res;
    }
    public final T executeAndReturnValue(final Connection conn, String query, Object... params) throws SQLException{
        T value;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            value = getResult(resultSet);
        }
        return value;
    }
    public final int executeAndReturnAggregate(final Connection conn, String query, Object... params) throws SQLException{
        int value = 0;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                value = resultSet.getInt(1);
            }
        }
        return value;
    }

    public final List<T> executeAndReturnList(final Connection conn, String query, Object... params) throws SQLException{
        List<T> values;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            values = getListOfResult(resultSet);
            }
        return values;
    }

    private void insertSQLParams(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

}
