package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnterCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        if (page.equals("login")) {
            return Path.LOGIN;
        }
        if (page.equals("signUp")) {
            return Path.SIGN_UP;
        }
        return Path.ERROR;
    }
}
