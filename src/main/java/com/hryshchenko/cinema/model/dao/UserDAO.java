package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.UserQueryBuilder;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO <String, User> {

    private final QueryBuilder<User> userQueryBuilder = new UserQueryBuilder();

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users;
        try {
            users = userQueryBuilder.executeAndReturnList(connection, Query.GET_ALL_USERS, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all user", e);
        }
        return users;
    }

    @Override
    public User findEntityByKey(String login) throws DAOException {
        User user;
        try {
            user = userQueryBuilder.executeAndReturnValue(connection, Query.GET_USER_BY_ID, login);
        } catch (SQLException e){
            throw new DAOException("problem in find user by login", e);
        }
        return user;
    }

    @Override
    public boolean create(User user) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(user.getLogin());
            params.add(user.getPassword());
            params.add(user.getName());
            params.add(user.getBalance());
            params.add(user.getRole().getId());

            result = userQueryBuilder.execute(connection, Query.CREATE_USER, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in create user", e);
        }
        return result;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        boolean result;
        try {
            result = userQueryBuilder.execute(connection, Query.DELETE_USER, user.getLogin());
        } catch (SQLException e){
            throw new DAOException("problem in delete user", e);
        }
        return result;
    }

    @Override
    public boolean update(User user) throws DAOException {
        boolean result;
        try {
            result = userQueryBuilder.execute(connection, Query.UPDATE_USER, user.getBalance(), user.getLogin());
        } catch (SQLException e){
            throw new DAOException("problem in update user", e);
        }
        return result;
    }
}
