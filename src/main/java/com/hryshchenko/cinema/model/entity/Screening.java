package com.hryshchenko.cinema.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Screening extends Entity {
    private static final long serialVersionUID = 1L;
    private long filmId;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private int stateId;

    public Screening(){ }

    public Screening(long id, long filmId, LocalDate filmDate, LocalTime timeBegin, int stateId) {
        super(id);
        this.filmId = filmId;
        this.filmDate = filmDate;
        this.timeBegin = timeBegin;
        this.stateId = stateId;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public LocalDate getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(LocalDate filmDate) {
        this.filmDate = filmDate;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "filmId=" + filmId +
                ", filmDate=" + filmDate +
                ", timeBegin=" + timeBegin +
                ", stateId=" + stateId +
                '}';
    }
}
