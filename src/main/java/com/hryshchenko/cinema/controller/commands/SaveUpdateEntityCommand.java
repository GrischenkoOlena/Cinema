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

public class SaveUpdateEntityCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String updateParam = (String) session.getAttribute("updateParam");

        String updateValue = req.getParameter("updateValue");
        switch (updateParam){
            case "login":{
                user.setLogin(updateValue);
                break;
            }
            case "name":{
                user.setName(updateValue);
                break;
            }
            case "balance": {
                user.setBalance(Double.parseDouble(updateValue));
                break;
            }
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
