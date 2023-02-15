package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.util.DataValidator;
import com.hryshchenko.cinema.util.PasswordHashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class SignUpCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private  UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", CommandUtils.getLocale(session));

        String login = req.getParameter("login");
        String password = req.getParameter("inputPassword");
        String repeatPassword = req.getParameter("repeatPassword");
        String userName = req.getParameter("userName");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", bundle.getString("error.empty.login"));
            return Path.SIGN_UP;
        }
        if (!password.equals(repeatPassword)){
            req.setAttribute("error", bundle.getString("error.identical.passwords"));
            return Path.SIGN_UP;
        }
        try {
            validateField(login, password, userName);
        } catch (FieldValidatorException e){
            req.setAttribute("error", e.getMessage());
            return Path.SIGN_UP;
        }

        log.info("New user is registered");
        return registerUser(session, login, password, userName);
    }

    private void validateField(String login, String password, String userName) throws FieldValidatorException {
        DataValidator.validateLogin(login);
        DataValidator.validatePassword(password);
        DataValidator.validateName(userName);
    }

    private String registerUser(HttpSession session, String login, String password, String userName) {
        String response;

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
            log.error(e.getMessage());
            response = Path.ERROR;
        }
        return response;
    }
}
