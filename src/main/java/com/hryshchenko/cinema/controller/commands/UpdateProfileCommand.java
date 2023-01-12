package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfileCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String updateName = req.getParameter("updateName");
        String updateBalance = req.getParameter("updateBalance");
        user.setName(updateName);
        try {
            user.setBalance(Double.parseDouble(updateBalance));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        UserService userService = AppContext.getInstance().getUserService();
        try {
            if (userService.updateUser(user)) {
                session.setAttribute("user", user);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return Path.PROFILE;
    }
}
