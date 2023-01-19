package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryExecutor;
import com.hryshchenko.cinema.model.builder.TicketSeatQueryExecutor;
import com.hryshchenko.cinema.model.entity.TicketSeat;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TicketSeatDAO extends AbstractDAO <Integer, TicketSeat> {
    private final QueryExecutor<TicketSeat> ticketSeatQueryExecutor = new TicketSeatQueryExecutor();
    @Override
    public List<TicketSeat> findAll() throws DAOException {
        return null;
    }

    @Override
    public Optional<TicketSeat> findEntityByKey(Integer id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public boolean delete(TicketSeat ticketSeat) throws DAOException {
        return false;
    }

    @Override
    public boolean create(TicketSeat ticketSeat) throws DAOException {
        boolean result;
        try {
            result = ticketSeatQueryExecutor.execute(connection,
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
