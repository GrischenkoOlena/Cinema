package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;
import com.hryshchenko.cinema.controller.commands.CommandUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Command for log out user. Available to registered user and admin.
 *
 *  @author Olena Hryshchenko
 */
public class LogoutCommand implements ICommand {
    private static final Logger log = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("Logout finished");
        CommandUtils.sendRedirectResponse(resp, Path.COMMAND_EMPTY);
        return Path.COMMAND_REDIRECT;
    }
}
