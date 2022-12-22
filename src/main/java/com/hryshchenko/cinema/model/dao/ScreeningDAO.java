package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Screening;

import java.util.List;

public class ScreeningDAO extends AbstractDAO <Integer, Screening> {
    @Override
    public List<Screening> findAll() throws DAOException {
        return null;
    }

    @Override
    public Screening findEntityByKey(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Screening screening) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Screening screening) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Screening screening) throws DAOException {
        return false;
    }
}
