package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Entity;

import java.sql.*;
import java.util.List;

/**
 * Abstract class for query executor.
 *
 * @author Olena Hryshchenko.
 */
public abstract class QueryExecutor<T extends Entity> {

    /**
     * Create object from ResultSet.
     *
     * @param rs the ResultSet
     * @return the {@link com.hryshchenko.cinema.model.entity.Entity}
     * @throws SQLException the SQL exception
     */
    public abstract T getResult (ResultSet rs) throws SQLException;

    /**
     * Create objects from ResultSet.
     *
     * @param rs the ResultSet
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Entity}
     * @throws SQLException the SQL exception
     */
    public abstract List<T> getListOfResult (ResultSet rs) throws SQLException;

    /**
     * Execute query (CREATE, UPDATE, DELETE) the database.
     *
     * @param conn tne connection from pool
     * @param query  the database query
     * @param params parameters of query
     * @throws SQLException the SQL exception
     */
    public boolean execute(final Connection conn, String query, Object... params) throws SQLException{
        boolean res;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            int rows = prepareStatement.executeUpdate();
            res = rows > 0;
        }
        return res;
    }

    /**
     * Reading from database.
     *
     * @param conn tne connection from pool
     * @param query    the database query
     * @param params parameters of query
     * @return the {@link com.hryshchenko.cinema.model.entity.Entity}
     * @throws SQLException the SQL exception
     */
    public T executeAndReturnValue(final Connection conn, String query, Object... params) throws SQLException{
        T value;
        try (PreparedStatement prepareStatement = conn.prepareStatement(query)) {
            insertSQLParams(prepareStatement, params);
            ResultSet resultSet = prepareStatement.executeQuery();
            value = getResult(resultSet);
        }
        return value;
    }

    /**
     * Execute an aggregate query.
     *
     * @param conn tne connection from pool
     * @param query    the database query
     * @param params parameters of query
     * @return an aggregate value
     * @throws SQLException the SQL exception
     */
    public int executeAndReturnAggregate(final Connection conn, String query, Object... params) throws SQLException{
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

    /**
     * Reading from database.
     *
     * @param conn tne connection from pool
     * @param query    the database query
     * @param params parameters of query
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Entity}
     * @throws SQLException the SQL exception
     */
    public List<T> executeAndReturnList(final Connection conn, String query, Object... params) throws SQLException{
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
