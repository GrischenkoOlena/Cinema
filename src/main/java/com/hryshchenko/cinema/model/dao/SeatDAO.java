package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.SeatQueryBuilder;
import com.hryshchenko.cinema.model.entity.Seat;

import java.sql.SQLException;
import java.util.List;

public class SeatDAO extends AbstractDAO <Integer, Seat> {
    private final QueryBuilder<Seat> seatQueryBuilder = new SeatQueryBuilder();

    @Override
    public List<Seat> findAll() throws DAOException {
        List<Seat> seats;
        try {
            seats = seatQueryBuilder.executeAndReturnList(connection, Query.GET_ALL_SEATS);
        } catch (SQLException e){
            throw new DAOException("problem in find all seats", e);
        }
        return seats;
    }

    @Override
    public Seat findEntityByKey(Integer id) throws DAOException {
        Seat seat;
        try {
            seat = seatQueryBuilder.executeAndReturnValue(connection, Query.GET_SEAT_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find seat by id", e);
        }
        return seat;
    }

    @Override
    public boolean delete(Seat seat) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Seat seat) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Seat seat) throws DAOException {
        boolean result;
        try {
            result = seatQueryBuilder.execute(connection, Query.UPDATE_SEAT, seat.getCategoryId(), seat.getId());
        } catch (SQLException e){
            throw new DAOException("problem in update seat", e);
        }
        return result;
    }
}
