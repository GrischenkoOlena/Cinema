package com.hryshchenko.cinema.model.entity;

public class TicketSeat extends Entity {
    private static final long serialVersionUID = 1L;
    private int ticketId;
    private int seatId;

    public TicketSeat(){}

    public TicketSeat (long id, int ticketId, int seatId) {
        super(id);
        this.ticketId = ticketId;
        this.seatId = seatId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
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
