package com.hryshchenko.cinema.model.entity;

public class Ticket extends Entity {
    private static final long serialVersionUID = 1L;
    private int screeningId;
    private int userId;
    private int ticketCount;

    public Ticket() {};

    public Ticket(long id, int screeningId, int userId, int ticketCount) {
        super(id);
        this.screeningId = screeningId;
        this.userId = userId;
        this.ticketCount = ticketCount;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "screeningId=" + screeningId +
                ", userId=" + userId +
                ", ticketCount=" + ticketCount +
                '}';
    }
}
