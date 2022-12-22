package com.hryshchenko.cinema.model.entity;

import java.util.Date;

public class Screening extends Entity {
    private static final long serialVersionUID = 1L;
    private int filmId;
    private Date timeBegin;
    private int stateId;

    public Screening(){ }

    public Screening(long id, int filmId, Date timeBegin, int stateId) {
        super(id);
        this.filmId = filmId;
        this.timeBegin = timeBegin;
        this.stateId = stateId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "filmId=" + filmId +
                ", timeBegin=" + timeBegin +
                ", stateId=" + stateId +
                '}';
    }
}
