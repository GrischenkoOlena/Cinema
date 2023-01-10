package com.hryshchenko.cinema.dto;

import com.hryshchenko.cinema.constant.enums.StatePlace;

public class SeatDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;

    private long id;
    private int line;
    private int place;
    private CategoryDTO category;

    private StatePlace state;

    public SeatDTO(){}

    public long getId() {
        return id;
    }

    public int getLine() {
        return line;
    }

    public int getPlace() {
        return place;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public StatePlace getState() {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
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
