package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.TicketDAO;
import com.hryshchenko.cinema.model.dao.TicketSeatDAO;
import com.hryshchenko.cinema.model.dao.UserDAO;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.TicketSeat;
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

    public boolean createTicket(Ticket ticket, List<TicketSeat> seats, User user) throws DAOException {
        UserDAO userDAO = new UserDAO();
        TicketSeatDAO ticketSeatDAO = new TicketSeatDAO();

        EntityTransaction entityTransaction = new EntityTransaction();

        entityTransaction.initTransaction(ticketDAO, userDAO, ticketSeatDAO);
        if (!ticketDAO.create(ticket)){
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
        }
        long ticket_id = ticketDAO.findNextAutoIncrement();
        for(TicketSeat seat : seats){
            seat.setTicketId(ticket_id);
            if(!ticketSeatDAO.create(seat)){
                entityTransaction.rollback();
                entityTransaction.endTransaction();
                return false;
            }
        }
        if(!userDAO.update(user)){
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
        }
        entityTransaction.commit();
        entityTransaction.endTransaction();
        return true;
    }
}
