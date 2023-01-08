package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.ScreeningDAO;
import com.hryshchenko.cinema.model.entity.Screening;

import java.sql.Connection;
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

    public Screening getScreeningById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningDAO.setConnection(conn);
        Screening screening = screeningDAO.findEntityByKey(id);
        dbManager.closeConnection(conn);
        return screening;
    }
}

