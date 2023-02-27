package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.ScreeningView;

import java.util.ArrayList;
import java.util.List;

public class MapperScreeningView implements IMapperService<ScreeningView, ScreeningViewDTO> {
    @Override
    public ScreeningViewDTO getDTO(ScreeningView entity) throws MapperException {
        ScreeningViewDTO screeningViewDTO = new ScreeningViewDTO();

        screeningViewDTO.setId(entity.getId());
        screeningViewDTO.setFilmTitle(entity.getFilmTitle());
        screeningViewDTO.setFilmDate(entity.getFilmDate());
        screeningViewDTO.setTimeBegin(entity.getTimeBegin());
        screeningViewDTO.setState(entity.getState());
        screeningViewDTO.setFreePlaces(entity.getFreePlaces());

        return screeningViewDTO;
    }

    @Override
    public List<ScreeningViewDTO> getListDTO(List<ScreeningView> entities) throws MapperException {
        List<ScreeningViewDTO> screeningDTOList = new ArrayList<>();
        for (ScreeningView screening : entities){
            screeningDTOList.add(getDTO(screening));
        }
        return screeningDTOList;
    }
}
