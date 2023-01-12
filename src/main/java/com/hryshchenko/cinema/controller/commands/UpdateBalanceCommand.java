package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class UpdateBalanceCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userLogin = req.getParameter("userLogin");
        String paramValue = req.getParameter("updateValue");

        UserService userService = AppContext.getInstance().getUserService();
        try {
            Optional<User> user = userService.getUserByLogin(userLogin);
            if(user.isPresent()){
                user.get().setBalance(Double.parseDouble(paramValue));
                userService.updateUser(user.get());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String forward = Path.COMMAND_ADMIN_CUSTOMERS;
        try {
            resp.sendRedirect(forward);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Path.COMMAND_REDIRECT;
    }
}
