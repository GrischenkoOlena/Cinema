package com.hryshchenko.cinema.model.builder;

import com.hryshchenko.cinema.model.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

public class SeatQueryExecutor extends QueryExecutor<Seat> {
    @Override
    public Seat getResult(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        while (rs.next()){
            seat.setId(rs.getInt(SEAT_ID));
            seat.setLine(rs.getInt(SEAT_LINE));
            seat.setPlace(rs.getInt(SEAT_PLACE));
            seat.setCategoryId(rs.getInt(SEAT_CATEGORY_ID));
        }
        return seat;
    }

    @Override
    public List<Seat> getListOfResult(ResultSet rs) throws SQLException {
        List<Seat> seats = new ArrayList<>();
        while (rs.next()){
            Seat seat = new Seat();
            seat.setId(rs.getInt(SEAT_ID));
            seat.setLine(rs.getInt(SEAT_LINE));
            seat.setPlace(rs.getInt(SEAT_PLACE));
            seat.setCategoryId(rs.getInt(SEAT_CATEGORY_ID));
            seats.add(seat);
        }
        return seats;
    }
}
