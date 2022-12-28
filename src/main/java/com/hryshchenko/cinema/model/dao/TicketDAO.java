package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.TicketQueryBuilder;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class TicketDAO extends AbstractDAO <Integer, Ticket> {

    private final QueryBuilder<Ticket> ticketQueryBuilder = new TicketQueryBuilder();
    @Override
    public List<Ticket> findAll() throws DAOException {
        return null;
    }

    @Override
    public Ticket findEntityByKey(Integer id) throws DAOException {
        Ticket ticket;
        try {
            ticket = ticketQueryBuilder.executeAndReturnValue(connection, Query.GET_TICKET_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find ticket by id", e);
        }
        return ticket;
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

    public List<Ticket> findTicketsByUser(User user) throws DAOException{
        List<Ticket> tickets;
        try {
            tickets = ticketQueryBuilder.executeAndReturnList(connection, Query.GET_TICKETS_BY_USER, user.getId());
        } catch (SQLException e){
            throw new DAOException("problem in find tickets by user", e);
        }
        return tickets;
    }
}
