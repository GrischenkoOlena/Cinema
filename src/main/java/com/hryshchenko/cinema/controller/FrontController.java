package com.hryshchenko.cinema.controller;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.UserDAO;
import com.hryshchenko.cinema.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class FrontController extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Hello from servlet\n");

        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.findAll();
            for (User user : users){
                resp.getWriter().write(user + "\n");
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
