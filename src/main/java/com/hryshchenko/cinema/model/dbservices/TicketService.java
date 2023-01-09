package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.TicketDAO;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class TicketService implements ICinemaService {
    private final TicketDAO ticketDAO;

    public TicketService() {
        ticketDAO = new TicketDAO();
    }

    public List<Ticket> getTicketsPageByUser(User user, long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        List<Ticket> tickets = ticketDAO.findPageTickets(user, begin, amount);
        dbManager.closeConnection(conn);
        return tickets;
    }

    public long getCountTicketByUser(User user) throws DAOException {
        Connection conn = dbManager.getConnection();
        ticketDAO.setConnection(conn);
        long count = ticketDAO.findCountTicketByUser(user);
        dbManager.closeConnection(conn);
        return count;
    }
}
