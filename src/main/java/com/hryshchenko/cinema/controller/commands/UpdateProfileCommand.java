package com.hryshchenko.cinema.controller.commands;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfileCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    private UserService userService = AppContext.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        try {
            updateUserInfo(req, user);
            if (userService.updateUser(user)) {
                session.setAttribute("user", user);
            }
        } catch (DAOException | FieldValidatorException e) {
            log.error(e.getMessage());
        }
        log.info(user.getLogin() + " profile's updated");

        try {
            resp.sendRedirect(Path.COMMAND_PROFILE);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Path.COMMAND_REDIRECT;
    }

    private void updateUserInfo(HttpServletRequest req, User user) throws FieldValidatorException {
        String updateName = req.getParameter("updateName");
        DataValidator.validateName(updateName);

        DataValidator.validateBalance(req.getParameter("updateBalance"));
        double updateBalance = Double.parseDouble(req.getParameter("updateBalance"));

        user.setName(updateName);
        user.setBalance(updateBalance);
    }
}
