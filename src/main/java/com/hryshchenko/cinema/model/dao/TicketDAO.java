package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Ticket;

import java.util.List;

public class TicketDAO extends AbstractDAO <Integer, Ticket> {
    @Override
    public List<Ticket> findAll() throws DAOException {
        return null;
    }

    @Override
    public Ticket findEntityByKey(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Ticket ticket) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Ticket ticket) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Ticket ticket) throws DAOException {
        return false;
    }
}
