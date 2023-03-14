package com.hryshchenko.cinema.controller.listener;

import com.hryshchenko.cinema.model.connectionpool.DBManager;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Context listener.
 *
 * @author Olena Hryshchenko.
 */
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
        DBManager.getInstance().closePool();
        log.debug("ConnectionPool was closed");
        try {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        } catch (SQLException e) {
            log.error("deregisterDriver error" + e.getMessage());
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
        log.debug("Servlet context destroy finished");
    }

    /**
     * Initializes CommandFactory.
     */
    private void initCommandFactory() {
        log.debug("ICommand container initialization started");

        try {
            Class.forName("com.hryshchenko.cinema.controller.commandFactory.CommandFactory");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        log.debug("ICommand container initialization finished");
    }


}
