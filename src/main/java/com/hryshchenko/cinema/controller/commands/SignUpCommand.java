package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.util.PasswordHashUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response;
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("inputPassword");
        String repeatPassword = req.getParameter("repeatPassword");
        String userName = req.getParameter("userName");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Login or password can't be empty");
            return Path.SIGN_UP;
        }
        if (!password.equals(repeatPassword)){
            req.setAttribute("error", "Passwords must be identical");
            return Path.SIGN_UP;
        }
        response = registerUser(session, login, password, userName);
        return response;
    }

    private String registerUser(HttpSession session, String login, String password, String userName) {
        String response = Path.ERROR;
        UserService userService = AppContext.getInstance().getUserService();

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(PasswordHashUtil.encode(password));
        newUser.setName(userName);
        newUser.setRole(UserRole.CLIENT);
        newUser.setBalance(0.0);
        try {
            if(userService.createUser(newUser)){
                session.setAttribute("user", newUser);
                session.setAttribute("userRole", UserRole.CLIENT);
                response = Path.SUCCESS;
            } else {
                response = Path.SIGN_UP;
            }
        } catch (DAOException e) {
            session.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
