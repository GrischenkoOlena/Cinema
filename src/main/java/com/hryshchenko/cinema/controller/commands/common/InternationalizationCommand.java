package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Command for redirect to current page after change language. Available to every user.
 *
 *  @author Olena Hryshchenko
 */
public class InternationalizationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CommandUtils.sendRedirectResponse(resp, req.getHeader("referer"));
        return Path.COMMAND_REDIRECT;
    }
}
