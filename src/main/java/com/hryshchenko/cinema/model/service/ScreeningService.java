package com.hryshchenko.cinema.model.service;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.SeatQueryBuilder;
import com.hryshchenko.cinema.model.dao.ScreeningDAO;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.Seat;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ScreeningService implements ICinemaService {
    private final ScreeningDAO screeningDAO;

    public ScreeningService() {
        this.screeningDAO = new ScreeningDAO();
    }

    public List<Screening> getScreeningByDate(LocalDate date) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        List<Screening> screenings = screeningDAO.findScreeningsByDate(date);
        dbManager.closeConnection(conn);
        return screenings;
    }
    public List<Seat> getFreeSeatByScreening(Screening screening) throws DAOException {
        List<Seat> seats;
        QueryBuilder<Seat> seatQueryBuilder = new SeatQueryBuilder();
        Connection conn = dbManager.getConnection();
        try {
            seats = seatQueryBuilder.executeAndReturnList(conn, Query.GET_FREE_SEAT_BY_SCREENING, screening.getId());
        } catch (SQLException e) {
            throw new DAOException("problem with get free seats", e);
        }
        dbManager.closeConnection(conn);
        return seats;
    }

    public int getCountFreeSeatByScreening(Screening screening) throws DAOException {
        int result;
        QueryBuilder<Seat> seatQueryBuilder = new SeatQueryBuilder();
        Connection conn = dbManager.getConnection();
        try {
            result = seatQueryBuilder.executeAndReturnAggregate(conn,
                    Query.GET_COUNT_FREE_SEAT_BY_SCREENING, screening.getId());
        } catch (SQLException e) {
            throw new DAOException("problem with get count free seats", e);
        }
        dbManager.closeConnection(conn);
        return result;
    }
}

