package com.hryshchenko.cinema.model.entity;

import java.util.Objects;

public class TicketSeat extends Entity {
    private static final long serialVersionUID = 1L;
    private long ticketId;
    private int seatId;

    public TicketSeat(){}

    public TicketSeat (long id, int ticketId, int seatId) {
        super(id);
        this.ticketId = ticketId;
        this.seatId = seatId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "TicketSeat{" +
                "ticketId=" + ticketId +
                ", seatId=" + seatId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketSeat that = (TicketSeat) o;
        return ticketId == that.ticketId && seatId == that.seatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, seatId);
    }
}
