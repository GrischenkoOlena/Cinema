package com.hryshchenko.cinema.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ScreeningViewDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;
    private long id;
    private String filmTitle;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private String state;
    private int freePlaces;

    public ScreeningViewDTO() {}

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public LocalDate getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(LocalDate filmDate) {
        this.filmDate = filmDate;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    @Override
    public String toString() {
        return "ScreeningViewDTO{" +
                ", filmTitle='" + filmTitle + '\'' +
                ", filmDate=" + filmDate +
                ", timeBegin=" + timeBegin +
                ", state='" + state + '\'' +
                ", freePlaces=" + freePlaces +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningViewDTO that = (ScreeningViewDTO) o;
        return id == that.id && freePlaces == that.freePlaces
                && Objects.equals(filmTitle, that.filmTitle)
                && Objects.equals(filmDate, that.filmDate)
                && Objects.equals(timeBegin, that.timeBegin)
                && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmTitle, filmDate, timeBegin, state, freePlaces);
    }
}
