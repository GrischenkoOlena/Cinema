package com.hryshchenko.cinema.dto;

import java.time.LocalDate;

public class AttendanceDTO implements ISimpleDTO{
    private static final long serialVersionUID = 1L;
    private LocalDate date;
    private int countSessions;
    private int countFilms;
    private int countFreeSeats;

    public AttendanceDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCountSessions() {
        return countSessions;
    }

    public void setCountSessions(int countSessions) {
        this.countSessions = countSessions;
    }

    public int getCountFilms() {
        return countFilms;
    }

    public void setCountFilms(int countFilms) {
        this.countFilms = countFilms;
    }

    public int getCountFreeSeats() {
        return countFreeSeats;
    }

    public void setCountFreeSeats(int countFreeSeats) {
        this.countFreeSeats = countFreeSeats;
    }

}
