package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.GenreService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapperFilm implements IMapperService<Film, FilmDTO> {
    @Override
    public FilmDTO getDTO(Film entity) throws MapperException {
        return new FilmDTO.FilmDTOBuilder(entity.getId())
                .title(entity.getTitle())
                .director(entity.getDirector())
                .cast(entity.getCast())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .genre(getGenreDTO(entity.getGenreID()))
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
        GenreService genreService = AppContext.getInstance().getGenreService();
        try {
            Optional<Genre> genre = genreService.getGenreById(genreId);
            if(genre.isPresent()){
                return genreMapperService.getDTO(genre.get());
            }
        } catch (DAOException e) {
            throw new MapperException("problem with mapping genre",e);
        }
        throw new MapperException("such genre is absent in BD");
    }

    public Film getFilm(FilmDTO filmDTO) throws MapperException {
        return new Film.FilmBuilder(filmDTO.getId())
                .title(filmDTO.getTitle())
                .director(filmDTO.getDirector())
                .cast(filmDTO.getCast())
                .description(filmDTO.getDescription())
                .genreId(filmDTO.getGenre().getId())
                .duration(filmDTO.getDuration())
                .build();
    }
}
