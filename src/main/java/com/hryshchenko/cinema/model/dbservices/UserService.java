package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.UserDAO;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserService extends CinemaService {
    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public Optional<User> getUserByLogin(String login) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        Optional<User> user = userDAO.findEntityByKey(login);
        dbManager.closeConnection(conn);
        return user;
    }

    public boolean createUser(User user) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        boolean result = userDAO.create(user);
        dbManager.closeConnection(conn);
        return result;
    }

    public boolean updateUser(User user) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        boolean result = userDAO.update(user);
        dbManager.closeConnection(conn);
        return result;
    }

    public List<User> getUsersPage(String order, long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        List<User> users = userDAO.findPageUsers(order, begin, amount);
        dbManager.closeConnection(conn);
        return users;
    }

    public long getCountUsers() throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        long count = userDAO.findCountUsers();
        dbManager.closeConnection(conn);
        return count;
    }

    public Optional<User> getUserById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        userDAO.setConnection(conn);
        Optional<User> user = userDAO.findUserById(id);
        dbManager.closeConnection(conn);
        return user;
    }
}
