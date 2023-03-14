package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Command for forward to the login page or sign up page. Available to every user.
 *
 *  @author Olena Hryshchenko
 */
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
