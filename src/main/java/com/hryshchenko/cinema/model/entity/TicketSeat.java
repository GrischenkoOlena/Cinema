package com.hryshchenko.cinema.model.entity;

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
}
