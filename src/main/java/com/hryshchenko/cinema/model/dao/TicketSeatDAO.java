package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.TicketSeat;

import java.util.List;

public class TicketSeatDAO extends AbstractDAO <Integer, TicketSeat> {
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
        return false;
    }

    @Override
    public boolean update(TicketSeat ticketSeat) throws DAOException {
        return false;
    }
}
