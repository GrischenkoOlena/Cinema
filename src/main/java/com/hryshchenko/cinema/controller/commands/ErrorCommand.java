package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String errorMessage = (String) req.getSession().getAttribute("error");
        if(errorMessage != null){
            req.getSession().removeAttribute("error");
        }
        req.setAttribute("error", errorMessage);
        return Path.ERROR;
    }
}
