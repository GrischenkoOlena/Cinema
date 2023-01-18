package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.TicketQueryBuilder;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TicketDAO extends AbstractDAO <Integer, Ticket> {

    private final QueryBuilder<Ticket> ticketQueryBuilder = new TicketQueryBuilder();
    @Override
    public List<Ticket> findAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<Ticket> findEntityByKey(Integer id) throws DAOException {
        Ticket ticket;
        try {
            ticket = ticketQueryBuilder.executeAndReturnValue(connection, Query.GET_TICKET_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find ticket by id", e);
        }
        return Optional.ofNullable(ticket);
    }

    @Override
    public boolean delete(Ticket ticket) throws DAOException {
        boolean result;
        try {
            result = ticketQueryBuilder.execute(connection, Query.DELETE_TICKET, ticket.getId());
        } catch (SQLException e){
            throw new DAOException("problem in delete ticket", e);
        }
        return result;
    }

    @Override
    public boolean create(Ticket ticket) throws DAOException {
        boolean result;
        try {
            result = ticketQueryBuilder.execute(connection,
                    Query.CREATE_TICKET, ticket.getScreeningId(), ticket.getUserId(), ticket.getTicketCount());
        } catch (SQLException e){
            throw new DAOException("problem in create ticket", e);
        }
        return result;
    }

    @Override
    public boolean update(Ticket ticket) throws DAOException {
        boolean result;
        try {
            result = ticketQueryBuilder.execute(connection,
                    Query.UPDATE_TICKET, ticket.getTicketCount(), ticket.getId());
        } catch (SQLException e){
            throw new DAOException("problem in update ticket", e);
        }
        return result;
    }

    public List<Ticket> findTicketsByUser(long userId) throws DAOException{
        List<Ticket> tickets;
        String query = Query.GET_TICKETS_BY_USER.replace("orderField", "");
        try {
            tickets = ticketQueryBuilder.executeAndReturnList(connection, query, userId);
        } catch (SQLException e){
            throw new DAOException("problem in find tickets by user", e);
        }
        return tickets;
    }

    public long findCountTicketByUser(long userId) throws DAOException {
        long result;
        try {
            result = ticketQueryBuilder.executeAndReturnAggregate(connection,Query.COUNT_TICKETS_BY_USER, userId);
        } catch (SQLException e){
            throw new DAOException("problem in find count of tickets", e);
        }
        return result;
    }
    public List<Ticket> findPageTickets(String order, long userId, long begin, long amount) throws DAOException {
        List<Ticket> screenings;
        String query = Query.GET_TICKETS_BY_USER.replace("orderField", order);
        try {
            screenings = ticketQueryBuilder.executeAndReturnList(connection, query,
                    userId, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find tickets by page", e);
        }
        return screenings;
    }

    public long findCountTicketByUserDate(long userId, LocalDate date) throws DAOException {
        long result;
        try {
            result = ticketQueryBuilder.executeAndReturnAggregate(connection,
                    Query.COUNT_TICKETS_BY_USER_DATE, userId, date);
        } catch (SQLException e){
            throw new DAOException("problem in find count of tickets by date", e);
        }
        return result;
    }
    public List<Ticket> findPageTicketsByUserDate(String order, long userId, LocalDate date, long begin, long amount)
            throws DAOException {
        List<Ticket> screenings;
        String query = Query.GET_TICKETS_BY_USER_DATE.replace("orderField", "ORDER BY " + order);
        try {
            screenings = ticketQueryBuilder.executeAndReturnList(connection, query,
                    userId, date, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find page tickets by date", e);
        }
        return screenings;
    }

    public long findNextAutoIncrement() throws DAOException {
        long result;
        try {
            result = ticketQueryBuilder.executeAndReturnAggregate(connection, Query.GET_NEXT_AUTOINCREMENT);
        } catch (SQLException e){
            throw new DAOException("problem in find count of tickets by date", e);
        }
        return result;
    }
}
