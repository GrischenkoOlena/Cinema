package com.hryshchenko.cinema.model.dbservices;

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
import java.util.Optional;

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

    public Optional<Screening> getScreeningById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        Optional<Screening> screening = screeningDAO.findEntityByKey(id);
        dbManager.closeConnection(conn);
        return screening;
    }

    public List<Screening> getScreeningsPage(String order,long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        List<Screening> screenings = screeningDAO.findPageScreenings(order, begin, amount);
        dbManager.closeConnection(conn);
        return screenings;
    }

    public long getCountScreening() throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        long count = screeningDAO.findCountScreenings();
        dbManager.closeConnection(conn);
        return count;
    }

    public int getCountAvailableSeatsById(long screeningId) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        int count = screeningDAO.findCountFreeSeatsById(screeningId);
        dbManager.closeConnection(conn);
        return count;
    }

    public List<Screening> getAvailableScreeningsPage(LocalDate filmDate, String order,long begin, long amount)
                        throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        List<Screening> screenings = screeningDAO.findPageAvailableScreenings(filmDate, order, begin, amount);
        dbManager.closeConnection(conn);
        return screenings;
    }

    public long getCountAvailableScreening(LocalDate filmDate) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        long count = screeningDAO.findCountAvailableScreenings(filmDate);
        dbManager.closeConnection(conn);
        return count;
    }

    public boolean createScreening(Screening screening) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        boolean result = screeningDAO.create(screening);
        dbManager.closeConnection(conn);
        return result;
    }

}

