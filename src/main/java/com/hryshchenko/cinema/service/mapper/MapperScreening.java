package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Screening;

import java.util.ArrayList;
import java.util.List;

public class MapperScreening implements IMapperService<Screening, ScreeningDTO> {
    @Override
    public ScreeningDTO getDTO(Screening entity) throws MapperException {
        ScreeningDTO screeningDTO = new ScreeningDTO();
        try {
            screeningDTO.setId(entity.getId());
            screeningDTO.setFilm(new FilmService().getFilmById(entity.getFilmId()));
            screeningDTO.setFilmDate(entity.getFilmDate());
            screeningDTO.setTimeBegin(entity.getTimeBegin());
            screeningDTO.setState(StateScreening.getValueFromId(entity.getStateId()));
        } catch (DAOException e) {
            throw new MapperException(e);
        }
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
}
