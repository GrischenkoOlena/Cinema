package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * Ticket entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class TicketQueryExecutor extends QueryExecutor<Ticket> {
    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.Ticket}
     */
    @Override
    public Ticket getResult(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        while (rs.next()){
            ticket.setId(rs.getInt(TICKET_ID));
            ticket.setScreeningId(rs.getInt(TICKET_SCREENING_ID));
            ticket.setUserId(rs.getInt(TICKET_USER_ID));
            ticket.setTicketCount(rs.getInt(TICKET_COUNT));
        }
        return ticket;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Ticket}
     */
    @Override
    public List<Ticket> getListOfResult(ResultSet rs) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        while (rs.next()){
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt(TICKET_ID));
            ticket.setScreeningId(rs.getInt(TICKET_SCREENING_ID));
            ticket.setUserId(rs.getInt(TICKET_USER_ID));
            ticket.setTicketCount(rs.getInt(TICKET_COUNT));
            tickets.add(ticket);
        }
        return tickets;
    }
}
