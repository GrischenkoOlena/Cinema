package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class MapperGenre implements IMapperService<Genre, GenreDTO> {
    @Override
    public GenreDTO getDTO(Genre entity) throws MapperException {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(entity.getId());
        genreDTO.setGenre(entity.getGenre());
        return genreDTO;
    }

    @Override
    public List<GenreDTO> getListDTO(List<Genre> entities) throws MapperException {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for(Genre genre : entities){
            genreDTOList.add(getDTO(genre));
        }
        return genreDTOList;
    }
}
