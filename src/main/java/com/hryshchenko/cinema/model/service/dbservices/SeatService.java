package com.hryshchenko.cinema.model.service.dbservices;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.constant.StatePlace;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.SeatQueryBuilder;
import com.hryshchenko.cinema.model.dao.SeatDAO;
import com.hryshchenko.cinema.model.service.dto.SeatDTO;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.Seat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SeatService implements ICinemaService {
    private final SeatDAO seatDAO;

    public SeatService() {
        this.seatDAO = new SeatDAO();
    }

    public List<Seat> getAllSeats() throws DAOException {
        Connection conn = dbManager.getConnection();
        seatDAO.setConnection(conn);
        List<Seat> seats = seatDAO.findAll();
        dbManager.closeConnection(conn);
        return seats;
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

    public SeatDTO[][] getFullFreeSeats(Screening screening) throws DAOException {
        int rows = getRowsCount();
        int cols = getPlacesCount();

        SeatDTO[][] seats = new SeatDTO[rows][cols];
        List<Seat> allSeats = getAllSeats();

        for(Seat seat : allSeats){
            int row = seat.getLine()-1;
            int col = seat.getPlace()-1;
            seats[row][col] = SeatDTO.build(seat);
        }
        setSoldSeat(screening, seats, allSeats);

        return seats;
    }

    private int getRowsCount() throws DAOException{
        Connection conn = dbManager.getConnection();
        seatDAO.setConnection(conn);
        int result = seatDAO.findMaxRow();
        dbManager.closeConnection(conn);
        return result;
    }

    private int getPlacesCount() throws DAOException{
        Connection conn = dbManager.getConnection();
        seatDAO.setConnection(conn);
        int result = seatDAO.findMaxPlace();
        dbManager.closeConnection(conn);
        return result;
    }

    private void setSoldSeat(Screening screening, SeatDTO[][] seats, List<Seat> allSeats) throws DAOException {
        List<Seat> freeSeats = getFreeSeatByScreening(screening);

        if (allSeats.size() != freeSeats.size()){
            for (Seat seat : allSeats) {
                if (!freeSeats.contains(seat)) {
                    int row = seat.getLine()-1;
                    int col = seat.getPlace()-1;
                    seats[row][col].setState(StatePlace.SOLD);
                }
            }
        }
    }

}
