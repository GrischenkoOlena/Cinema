package com.hryshchenko.cinema.model.entity;

import java.util.Objects;

public class Seat extends Entity{
    private static final long serialVersionUID = 1L;
    private int line;
    private int place;
    private int categoryId;

    public Seat() {}

    public Seat(int id, int line, int place, int categoryId) {
        super(id);
        this.line = line;
        this.place = place;
        this.categoryId = categoryId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "line=" + line +
                ", place=" + place +
                ", categoryId=" + categoryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return line == seat.line && place == seat.place && categoryId == seat.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, place, categoryId);
    }
}
