package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Entity;

import java.sql.Connection;
import java.util.List;


public abstract class AbstractDAO <K, T extends Entity> {
    protected Connection connection;
    public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityByKey (K id) throws DAOException;
    public abstract boolean create(T t) throws DAOException;
    public abstract boolean delete(T t) throws DAOException;
    public abstract boolean update (T t) throws DAOException;

    public void setConnection(Connection conn) {
        this.connection = conn;
    }

}
