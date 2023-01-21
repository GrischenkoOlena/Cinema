package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.TicketSeat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

public class TicketSeatQueryExecutor extends QueryExecutor<TicketSeat> {
    @Override
    public TicketSeat getResult(ResultSet rs) throws SQLException {
        TicketSeat ticketSeat = new TicketSeat();
        while (rs.next()){
            ticketSeat.setId(rs.getInt(TICKET_SEAT_ID));
            ticketSeat.setTicketId(rs.getInt(TICKET_SEAT_TICKET_ID));
            ticketSeat.setSeatId(rs.getInt(TICKET_SEAT_SEAT_ID));
        }
        return ticketSeat;
    }

    @Override
    public List<TicketSeat> getListOfResult(ResultSet rs) throws SQLException {
        List<TicketSeat> ticketSeats = new ArrayList<>();
        while (rs.next()){
            TicketSeat ticketSeat = new TicketSeat();
            ticketSeat.setId(rs.getInt(TICKET_SEAT_ID));
            ticketSeat.setTicketId(rs.getInt(TICKET_SEAT_TICKET_ID));
            ticketSeat.setSeatId(rs.getInt(TICKET_SEAT_SEAT_ID));
            ticketSeats.add(ticketSeat);
        }
        return ticketSeats;
    }
}
