package com.hryshchenko.cinema.model.service.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.UserDAO;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.Connection;
import java.util.Optional;

public class UserService implements ICinemaService{
    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public Optional<User> getUserByLogin(String login) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        Optional<User> user = Optional.of(userDAO.findEntityByKey(login));
        dbManager.closeConnection(conn);
        return user;
    }
}
