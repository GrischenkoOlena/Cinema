package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.model.dbservices.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String response = Path.HOME;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Login or password can't be empty");
            response = Path.LOGIN;
        }

        UserService userService = AppContext.getInstance().getUserService();
        try {
            Optional<User> user = userService.getUserByLogin(login);
            if(user.isPresent()) {
                if (user.get().getPassword().equals(password)) {
                    UserRole userRole = user.get().getRole();
                    if (userRole == UserRole.ADMIN) {
                        response = Path.ADMIN_MAIN;
                    }
                    if (userRole == UserRole.CLIENT) {
                        response = Path.USER_MAIN;
                    }
                    session.setAttribute("user", user.get());
                    session.setAttribute("userRole", userRole);
                } else {
                    req.setAttribute("login", login);
                    req.setAttribute("error", "Login or password isn't correct");
                    response = Path.LOGIN;
                }
            } else {
                req.setAttribute("error", "Login isn't exists");
                response = Path.SIGN_UP;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
