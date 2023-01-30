package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.FieldValidatorException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.util.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateBalanceCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private final UserService userService = AppContext.getInstance().getUserService();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userLogin = req.getParameter("userLogin");

        try {
            Optional<User> user = userService.getUserByLogin(userLogin);
            if(user.isPresent()){
                user.get().setBalance(getUpdateBalance(req));
                userService.updateUser(user.get());
            }
        } catch (DAOException | FieldValidatorException e) {
            log.error(e.getMessage());
        }

        String forward = Path.COMMAND_ADMIN_CUSTOMERS;
        sendRedirectResponse(resp, forward);
        log.info("User balance's updated");
        return Path.COMMAND_REDIRECT;
    }

    private void sendRedirectResponse(HttpServletResponse resp, String forward) {
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private double getUpdateBalance(HttpServletRequest req) throws FieldValidatorException {
        DataValidator.validateBalance(req.getParameter("updateBalance"));
        return Double.parseDouble(req.getParameter("updateBalance"));
    }
}
