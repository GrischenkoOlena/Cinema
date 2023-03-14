package com.hryshchenko.cinema.controller.commandFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author Olena Hryshchenko
 */
public interface ICommand {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    String execute (HttpServletRequest req, HttpServletResponse resp);
}
