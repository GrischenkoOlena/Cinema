package com.hryshchenko.cinema.model.dto;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.State;
import com.hryshchenko.cinema.model.service.FilmService;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Film film;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private State state;


    public ScreeningDTO() {
    }
    public static ScreeningDTO build(Screening screening) throws DAOException {
        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.film = new FilmService().getFilmById(screening.getFilmId());
        screeningDTO.filmDate = screening.getFilmDate();
        screeningDTO.timeBegin = screening.getTimeBegin();
        screeningDTO.state = State.getValueFromId(screening.getStateId());
        return screeningDTO;
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

    public State getState() {
        return state;
    }
}
