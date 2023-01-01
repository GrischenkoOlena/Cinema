package com.hryshchenko.cinema.model.dto;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.State;
import com.hryshchenko.cinema.model.service.FilmService;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningDTO implements ISimpleDTO {
    private static final long serialVersionUID = 1L;

    private long id;
    private Film film;
    private LocalDate filmDate;
    private LocalTime timeBegin;
    private State state;


    private ScreeningDTO() {
    }
    public static ScreeningDTO build(Screening screening) throws DAOException {
        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.id = screening.getId();
        screeningDTO.film = new FilmService().getFilmById(screening.getFilmId());
        screeningDTO.filmDate = screening.getFilmDate();
        screeningDTO.timeBegin = screening.getTimeBegin();
        screeningDTO.state = State.getValueFromId(screening.getStateId());
        return screeningDTO;
    }

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

    public State getState() {
        return state;
    }
}
