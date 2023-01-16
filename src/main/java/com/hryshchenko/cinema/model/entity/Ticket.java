package com.hryshchenko.cinema.model.entity;

public class Ticket extends Entity {
    private static final long serialVersionUID = 1L;
    private long screeningId;
    private long userId;
    private int ticketCount;

    public Ticket() {}

    public Ticket(long id, long screeningId, long userId, int ticketCount) {
        super(id);
        this.screeningId = screeningId;
        this.userId = userId;
        this.ticketCount = ticketCount;
    }

    public long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(long screeningId) {
        this.screeningId = screeningId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
