package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.TicketDAO;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TicketService implements ICinemaService {
    private final TicketDAO ticketDAO;

    public TicketService() {
        ticketDAO = new TicketDAO();
    }

    public List<Ticket> getTicketsPageByUser(String order, long userId, long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        List<Ticket> tickets = ticketDAO.findPageTickets(order, userId, begin, amount);
        dbManager.closeConnection(conn);
        return tickets;
    }

    public long getCountTicketByUser(long userId) throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        long count = ticketDAO.findCountTicketByUser(userId);
        dbManager.closeConnection(conn);
        return count;
    }

    public List<Ticket> getTicketsPageByUserDate(String order, long userId, LocalDate date, long begin, long amount)
            throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        List<Ticket> tickets = ticketDAO.findPageTicketsByUserDate(order, userId, date, begin, amount);
        dbManager.closeConnection(conn);
        return tickets;
    }

    public long getCountTicketByUserDate(long userId, LocalDate date) throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        long count = ticketDAO.findCountTicketByUserDate(userId, date);
        dbManager.closeConnection(conn);
        return count;
    }
}
