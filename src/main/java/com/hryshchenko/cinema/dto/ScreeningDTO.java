package com.hryshchenko.cinema.dto;

import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.constant.enums.StateScreening;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningDTO implements ISimpleDTO {
    private static final long serialVersionUID = 1L;

    private long id;
    private Film film;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private StateScreening state;
    private int availableSeats;

    public ScreeningDTO() { }

    public long getId() {
        return id;
    }

    public String  getFilm() {
        return film.getTitle();
    }

    public LocalDate getFilmDate() {
        return filmDate;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public StateScreening getState() {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setFilmDate(LocalDate filmDate) {
        this.filmDate = filmDate;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setState(StateScreening state) {
        this.state = state;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "ScreeningDTO{" +
                "film=" + film +
                ", filmDate=" + filmDate +
                ", timeBegin=" + timeBegin +
                ", state=" + state +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
