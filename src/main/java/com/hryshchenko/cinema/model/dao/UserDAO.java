package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.UserQueryBuilder;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO <String, User> {

    private final QueryBuilder<User> userQueryBuilder = new UserQueryBuilder();

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users;
        String query = Query.GET_ALL_USERS.replace("orderField", "");
        try {
            users = userQueryBuilder.executeAndReturnList(connection, query, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all user", e);
        }
        return users;
    }

    @Override
    public Optional<User> findEntityByKey(String login) throws DAOException {
        User user;
        try {
            user = userQueryBuilder.executeAndReturnValue(connection, Query.GET_USER_BY_LOGIN, login);
            user = user.getLogin() != null ? user : null;
        } catch (SQLException e){
            throw new DAOException("problem in find user by login", e);
        }
        return Optional.ofNullable(user);
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
            result = userQueryBuilder.execute(connection, Query.UPDATE_USER,
                                user.getLogin(), user.getName(), user.getBalance(), user.getId());
        } catch (SQLException e){
            throw new DAOException("problem in update user", e);
        }
        return result;
    }

    public long findCountUsers() throws DAOException {
        long result;
        try {
            result = userQueryBuilder.executeAndReturnAggregate(connection,Query.COUNT_USER);
        } catch (SQLException e){
            throw new DAOException("problem in find count of users", e);
        }
        return result;
    }
    public List<User> findPageUsers(String order, long begin, long amount) throws DAOException {
        List<User> screenings;
        String query = Query.GET_ALL_USERS.replace("orderField", "ORDER BY " + order);
        try {
            screenings = userQueryBuilder.executeAndReturnList(connection, query, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find users by page", e);
        }
        return screenings;
    }

    public Optional<User> findUserById(long id) throws DAOException {
        User user;
        try {
            user = userQueryBuilder.executeAndReturnValue(connection, Query.GET_USER_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find user by id", e);
        }
        return Optional.ofNullable(user);
    }
}
