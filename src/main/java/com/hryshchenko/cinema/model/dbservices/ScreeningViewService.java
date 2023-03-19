package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.dto.AttendanceDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.ScreeningViewDAO;
import com.hryshchenko.cinema.model.entity.ScreeningView;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class ScreeningViewService extends CinemaService {
    private final ScreeningViewDAO screeningViewDAO;

    public ScreeningViewService() {
        this.screeningViewDAO = new ScreeningViewDAO();
    }

    public List<ScreeningView> getScreeningViewsPage(String order, long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        List<ScreeningView> screenings = screeningViewDAO.findPageScreeningViews(order, begin, amount);
        dbManager.closeConnection(conn);
        return screenings;
    }

    public long getCountScreeningView() throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        long count = screeningViewDAO.findCountScreeningViews();
        dbManager.closeConnection(conn);
        return count;
    }
    public List<ScreeningView> getAvailableScreeningViewsPage(LocalDate date, String order, long begin, long amount)
                        throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        List<ScreeningView> screenings = screeningViewDAO.findPageAvailableScreeningViews(date, order, begin, amount);
        dbManager.closeConnection(conn);
        return screenings;
    }

    public long getCountAvailableScreeningView(LocalDate date) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        long count = screeningViewDAO.findCountAvailableScreeningViews(date);
        dbManager.closeConnection(conn);
        return count;
    }

    public List<AttendanceDTO> getAttendancePage(long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        List<AttendanceDTO> screenings = screeningViewDAO.findPageAttendance(begin, amount);
        dbManager.closeConnection(conn);
        return screenings;
    }

    public long getCountAttendance() throws DAOException {
        Connection conn = dbManager.getConnection();
        screeningViewDAO.setConnection(conn);
        long count = screeningViewDAO.findCountAttendances();
        dbManager.closeConnection(conn);
        return count;
    }
}
