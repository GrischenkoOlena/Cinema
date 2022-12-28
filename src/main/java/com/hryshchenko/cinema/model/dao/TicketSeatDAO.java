package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.TicketSeatQueryBuilder;
import com.hryshchenko.cinema.model.entity.TicketSeat;

import java.sql.SQLException;
import java.util.List;

public class TicketSeatDAO extends AbstractDAO <Integer, TicketSeat> {
    private final QueryBuilder<TicketSeat> ticketSeatQueryBuilder = new TicketSeatQueryBuilder();
    @Override
    public List<TicketSeat> findAll() throws DAOException {
        return null;
    }

    @Override
    public TicketSeat findEntityByKey(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(TicketSeat ticketSeat) throws DAOException {
        return false;
    }

    @Override
    public boolean create(TicketSeat ticketSeat) throws DAOException {
        boolean result;
        try {
            result = ticketSeatQueryBuilder.execute(connection,
                    Query.CREATE_TICKET_SEAT, ticketSeat.getTicketId(), ticketSeat.getSeatId());
        } catch (SQLException e){
            throw new DAOException("problem in delete ticket", e);
        }
        return result;
    }

    @Override
    public boolean update(TicketSeat ticketSeat) throws DAOException {
        return false;
    }
}
