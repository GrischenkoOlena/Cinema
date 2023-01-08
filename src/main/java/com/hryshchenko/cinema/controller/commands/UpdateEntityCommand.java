package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateEntityCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String updateParam = req.getParameter("updateParam");
        String paramValue = "";
        switch (updateParam){
            case "login": {
                paramValue = user.getLogin();
                break;
            }
            case "name": {
                paramValue = user.getName();
                break;
            }
            case "balance": {
                paramValue = Double.toString(user.getBalance());
                break;
            }
        }
        req.setAttribute("updateParam", updateParam);
        req.setAttribute("paramValue", paramValue);
        session.setAttribute("updateParam", updateParam);

        return Path.UPDATE_ENTITY;
    }
}
