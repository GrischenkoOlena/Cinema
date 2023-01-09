package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.GenreService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class MapperFilm implements IMapperService<Film, FilmDTO> {
    @Override
    public FilmDTO getDTO(Film entity) throws MapperException {
        GenreDTO genreDTO = getGenreDTO(entity.getGenreID());

        return new FilmDTO.FilmDTOBuilder(entity.getId())
                .title(entity.getTitle())
                .director(entity.getDirector())
                .cast(entity.getCast())
                .description(entity.getDescription())
                .genre(genreDTO)
                .build();
    }

    @Override
    public List<FilmDTO> getListDTO(List<Film> entities) throws MapperException {
        List<FilmDTO> filmDTOList = new ArrayList<>();
        for(Film film : entities){
            filmDTOList.add(getDTO(film));
        }
        return filmDTOList;
    }

    private GenreDTO getGenreDTO(long genreId) throws MapperException {
        IMapperService<Genre, GenreDTO> genreMapperService = new MapperGenre();
        GenreDTO genreDTO;
        try {
            genreDTO = genreMapperService.getDTO(new GenreService().getGenreById(genreId));
        } catch (DAOException e) {
            throw new MapperException("problem with mapping genre",e);
        }
        return genreDTO;
    }
}
