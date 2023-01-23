package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.IncorrectPasswordException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.util.PasswordHashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String response = Path.HOME;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Login or password can't be empty");
            return Path.LOGIN;
        }

        try {
            Optional<User> user = userService.getUserByLogin(login);
            if(user.isPresent()) {
                String forward = Path.ERROR;
                try {
                    PasswordHashUtil.verify(user.get().getPassword(), password);
                    UserRole userRole = user.get().getRole();
                    if (userRole == UserRole.ADMIN) {
                        forward = Path.COMMAND_ADMIN_SCREENINGS;
                    }
                    if (userRole == UserRole.CLIENT) {
                        forward = Path.COMMAND_USER_SCHEDULE;
                    }
                    resp.sendRedirect(forward);
                    response = Path.COMMAND_REDIRECT;
                    session.removeAttribute("errorBuyTicket");
                    session.setAttribute("user", user.get());
                    session.setAttribute("userRole", userRole);
                    log.info("user " + login + " is login");
                } catch (IncorrectPasswordException e) {
                    req.setAttribute("login", login);
                    String errorMessage = "Login or password isn't correct";
                    req.setAttribute("error", errorMessage);
                    log.error(errorMessage);
                    response = Path.LOGIN;
                }
            } else {
                String errorMessage = "Login isn't exists";
                req.setAttribute("error", errorMessage);
                log.error(errorMessage);
                response = Path.SIGN_UP;
            }
        } catch (DAOException | IOException e) {
            log.error(e.getMessage());
        }
        return response;
    }
}
