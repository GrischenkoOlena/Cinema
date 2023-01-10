package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Screening;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapperScreening implements IMapperService<Screening, ScreeningDTO> {
    @Override
    public ScreeningDTO getDTO(Screening entity) throws MapperException {

        ScreeningDTO screeningDTO = new ScreeningDTO();
            screeningDTO.setId(entity.getId());
            screeningDTO.setFilm(getFilmDTO(entity.getFilmId()));
            screeningDTO.setFilmDate(entity.getFilmDate());
            screeningDTO.setTimeBegin(entity.getTimeBegin());
            screeningDTO.setState(StateScreening.getValueFromId(entity.getStateId()));
            screeningDTO.setAvailableSeats(getAvailableSeatsById(entity.getId()));
        return screeningDTO;
    }

    @Override
    public List<ScreeningDTO> getListDTO(List<Screening> entities) throws MapperException {
        List<ScreeningDTO> screeningDTOList = new ArrayList<>();
        for (Screening screening : entities){
            screeningDTOList.add(getDTO(screening));
        }
        return screeningDTOList;
    }

    private int getAvailableSeatsById(long screeningId) throws MapperException {
        ScreeningService screeningService = AppContext.getInstance().getScreeningService();
        try {
            return screeningService.getCountAvailableSeatsById(screeningId);
        } catch (DAOException e) {
            throw new MapperException("problem with mapper screening", e);
        }
    }

    private FilmDTO getFilmDTO(long filmId) throws MapperException {
        FilmService filmService = AppContext.getInstance().getFilmService();
        IMapperService<Film, FilmDTO> mapperService = new MapperFilm();
        try {
            Optional<Film> film = filmService.getFilmById(filmId);
            if(film.isPresent()){
                return mapperService.getDTO(film.get());
            }
        } catch (DAOException e) {
            throw new MapperException("problem with mapper screening", e);
        }
        throw new MapperException("such film is absent in BD");
    }
}
