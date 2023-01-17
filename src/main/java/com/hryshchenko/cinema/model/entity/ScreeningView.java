package com.hryshchenko.cinema.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningView extends Entity{
    private static final long serialVersionUID = 1L;
    private String filmTitle;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private String state;
    private int freePlaces;

    public ScreeningView() { }

    public ScreeningView(long id, String filmTitle, LocalDate filmDate, LocalTime timeBegin, String state, int freePlaces) {
        super(id);
        this.filmTitle = filmTitle;
        this.filmDate = filmDate;
        this.timeBegin = timeBegin;
        this.state = state;
        this.freePlaces = freePlaces;
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
        return "ScreeningView{" +
                "filmTitle='" + filmTitle + '\'' +
                ", filmDate=" + filmDate +
                ", timeBegin=" + timeBegin +
                ", state='" + state + '\'' +
                ", freePlaces=" + freePlaces +
                '}';
    }
}
