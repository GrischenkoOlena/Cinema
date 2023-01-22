package com.hryshchenko.cinema.dto;

import com.hryshchenko.cinema.model.entity.Seat;

import java.util.List;
import java.util.Objects;

public class TicketDTO implements ISimpleDTO {
    private long id;
    private ScreeningDTO screening;
    private UserDTO user;
    private int ticketCount;
    private List<SeatDTO> seats;

    public TicketDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ScreeningDTO getScreening() {
        return screening;
    }

    public void setScreening(ScreeningDTO screening) {
        this.screening = screening;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", screening=" + screening +
                ", user=" + user +
                ", ticketCount=" + ticketCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO ticketDTO = (TicketDTO) o;
        return id == ticketDTO.id && ticketCount == ticketDTO.ticketCount
                && Objects.equals(screening, ticketDTO.screening)
                && Objects.equals(user, ticketDTO.user)
                && Objects.equals(seats, ticketDTO.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, screening, user, ticketCount, seats);
    }
}
