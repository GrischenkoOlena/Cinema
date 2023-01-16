package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.constant.enums.StatePlace;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.builder.SeatQueryBuilder;
import com.hryshchenko.cinema.model.dao.SeatDAO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.service.mapper.IMapperService;
import com.hryshchenko.cinema.service.mapper.MapperSeat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    public Optional<Seat> getSeatById(int seatId) throws DAOException {
        Connection conn = dbManager.getConnection();
        seatDAO.setConnection(conn);
        Optional<Seat> seat = seatDAO.findEntityByKey(seatId);
        dbManager.closeConnection(conn);
        return seat;
    }

    public List<Seat> getFreeSeatByScreening(long screeningId) throws DAOException {
        List<Seat> seats;
        QueryBuilder<Seat> seatQueryBuilder = new SeatQueryBuilder();
        Connection conn = dbManager.getConnection();
        try {
            seats = seatQueryBuilder.executeAndReturnList(conn, Query.GET_FREE_SEAT_BY_SCREENING, screeningId);
        } catch (SQLException e) {
            throw new DAOException("problem with get free seats", e);
        }
        dbManager.closeConnection(conn);
        return seats;
    }

    public List<Seat> getSeatsByTicket(Ticket ticket) throws DAOException {
        Connection conn = dbManager.getConnection();
        seatDAO.setConnection(conn);
        List<Seat> seats = seatDAO.getSeatByTicket(ticket.getId());
        dbManager.closeConnection(conn);
        return seats;
    }


    public SeatDTO[][] getFullFreeSeats(Screening screening) throws DAOException, MapperException {
        IMapperService<Seat, SeatDTO> mapperService = new MapperSeat();
        int rows = getRowsCount();
        int cols = getPlacesCount();

        SeatDTO[][] seats = new SeatDTO[rows][cols];
        List<Seat> allSeats = getAllSeats();

        for(Seat seat : allSeats){
            int row = seat.getLine()-1;
            int col = seat.getPlace()-1;
            seats[row][col] = mapperService.getDTO(seat);
        }
        setSoldSeat(screening.getId(), seats, allSeats);

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

    private void setSoldSeat(long screeningId, SeatDTO[][] seats, List<Seat> allSeats) throws DAOException {
        List<Seat> freeSeats = getFreeSeatByScreening(screeningId);

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
