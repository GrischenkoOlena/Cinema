package com.hryshchenko.cinema.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("Servlet context initialization starts");
        initCommandFactory();
        log.debug("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private void initCommandFactory() {
        log.debug("ICommand container initialization started");

        try {
            Class.forName("com.hryshchenko.cinema.controller.CommandFactory");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        log.debug("ICommand container initialization finished");
    }


}
