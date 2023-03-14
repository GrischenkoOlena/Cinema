package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *  Command for login user with authentication. Available to every user.
 *
 *  @author Olena Hryshchenko
 */
public class LoginCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", CommandUtils.getLocale(session));

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String response = Path.HOME;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", bundle.getString("error.empty.login"));
            return Path.LOGIN;
        }

        try {
            Optional<User> user = userService.getUserByLogin(login);
            if(user.isPresent()) {
                try {
                    response = getResponseLoginExistsUser(resp, session, login, password, user.get());
                } catch (IncorrectPasswordException e) {
                    response = getResponseBadPassword(req, login, bundle);
                }
            } else {
                response = getResponseNotExistsUser(req, bundle);
            }
        } catch (DAOException e) {
            log.error(e.getMessage());
        }
        return response;
    }

    private String getResponseLoginExistsUser(HttpServletResponse resp, HttpSession session,
                                              String login, String password, User user)
            throws IncorrectPasswordException {

        String forward = Path.ERROR;
        PasswordHashUtil.verify(user.getPassword(), password);
        UserRole userRole = user.getRole();
        if (userRole == UserRole.ADMIN) {
            forward = Path.COMMAND_ADMIN_SCREENINGS;
        }
        if (userRole == UserRole.CLIENT) {
            forward = Path.COMMAND_USER_SCHEDULE;
        }

        CommandUtils.sendRedirectResponse(resp, forward);
        session.removeAttribute("errorBuyTicket");
        session.setAttribute("user", user);
        session.setAttribute("userRole", userRole);
        log.info("user " + login + " is login");
        return Path.COMMAND_REDIRECT;
    }

    private String getResponseBadPassword(HttpServletRequest req, String login, ResourceBundle bundle) {
        req.setAttribute("login", login);
        req.setAttribute("error", bundle.getString("error.bad.password"));
        log.error("Login or password is incorrect");
        return Path.LOGIN;
    }

    private String getResponseNotExistsUser(HttpServletRequest req, ResourceBundle bundle) {
        req.setAttribute("error", bundle.getString("error.not.exist.user"));
        log.error("Login doesn't exists");
        return Path.SIGN_UP;
    }
}
