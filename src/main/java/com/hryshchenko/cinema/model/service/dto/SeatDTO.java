package com.hryshchenko.cinema.model.service.dto;

import com.hryshchenko.cinema.constant.StatePlace;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.service.dbservices.CategoryService;

public class SeatDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;

    private long id;
    private int line;
    private int place;
    private Category category;


    private StatePlace state;

    private SeatDTO(){}

    public static SeatDTO build(Seat seat) throws DAOException {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.id = seat.getId();
        seatDTO.line = seat.getLine();
        seatDTO.place = seat.getPlace();
        seatDTO.category = new CategoryService().getCategoryByID(seat.getCategoryId());
        seatDTO.state = StatePlace.FREE;
        return seatDTO;
    }

    public long getId() {
        return id;
    }

    public int getLine() {
        return line;
    }

    public int getPlace() {
        return place;
    }

    public Category getCategory() {
        return category;
    }

    public StatePlace getState() {
        return state;
    }

    public void setState(StatePlace state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "id=" + id +
                ", line=" + line +
                ", place=" + place +
                ", category=" + category +
                ", state=" + state +
                '}';
    }
}
