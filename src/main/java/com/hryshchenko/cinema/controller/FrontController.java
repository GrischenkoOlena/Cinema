package com.hryshchenko.cinema.controller;

import com.hryshchenko.cinema.controller.commandFactory.CommandFactory;
import com.hryshchenko.cinema.controller.commandFactory.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller",
            initParams = {
                @WebInitParam(name = "javax.servlet.jsp.jstl.fmt.localizationContext", value = "resources"),
                @WebInitParam(name = "javax.servlet.jsp.jstl.fmt.locale", value = "en"),
                @WebInitParam(name = "locales", value = "en ua")
            })
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand ic = commandFactory.getCommand(req);
        String page = ic.execute(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (!page.equals("redirect")) {
            dispatcher.forward(req, resp);
        }
    }

}
