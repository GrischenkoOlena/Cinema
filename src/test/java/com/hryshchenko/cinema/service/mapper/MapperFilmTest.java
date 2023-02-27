package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.GenreService;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperFilmTest {
    Film testFilm = new Film();
    FilmDTO testDTO;
    GenreDTO testGenreDTO;

    @Mock
    GenreService genreService;
    @Mock
    IMapperService<Genre, GenreDTO> genreMapperService;

    @BeforeEach
    void setUp() {
        testGenreDTO = new GenreDTO(1L, "historical");

        testFilm = new Film.FilmBuilder(1L)
                .title("Avatar")
                .director("Cameron")
                .cast("Caprio")
                .duration(180)
                .genreId(1L)
                .build();

        testDTO = new FilmDTO.FilmDTOBuilder(1L)
                .title("Avatar")
                .director("Cameron")
                .cast("Caprio")
                .genre(testGenreDTO)
                .duration(180).build();
    }

    @Test
    void getDTOTest() throws DAOException, MapperException {
        MapperFilm mapper = new MapperFilm(genreService, genreMapperService);
        Mockito.when(genreService.getGenreById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Genre(1L, "historical")));

        Mockito.when(genreMapperService.getDTO(Mockito.any())).thenReturn(testGenreDTO);
        assertEquals(testDTO, mapper.getDTO(testFilm));
    }

    @Test
    void getListDTO() throws DAOException, MapperException {
        List<FilmDTO> dtoList = new ArrayList<>();
        dtoList.add(testDTO);

        List<Film> entityList = new ArrayList<>();
        entityList.add(testFilm);

        MapperFilm mapper = new MapperFilm(genreService, genreMapperService);
        Mockito.when(genreService.getGenreById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Genre(1L, "historical")));
        Mockito.when(genreMapperService.getDTO(Mockito.any())).thenReturn(testGenreDTO);

        assertEquals(dtoList, mapper.getListDTO(entityList));
    }

    @Test
    void getFilm() throws MapperException {
        MapperFilm mapper = new MapperFilm();
        assertEquals(testFilm, mapper.getFilm(testDTO));
    }

    @Test
    void getDTOWithException() throws DAOException {
        MapperFilm mapper = new MapperFilm(genreService, genreMapperService);
        Mockito.when(genreService.getGenreById(Mockito.anyLong())).thenThrow(DAOException.class);
        assertThrows(MapperException.class, ()-> mapper.getDTO(testFilm));
    }

    @Test
    void getDTOEmpty() throws DAOException {
        MapperFilm mapper = new MapperFilm(genreService, genreMapperService);
        Mockito.when(genreService.getGenreById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(MapperException.class, ()-> mapper.getDTO(testFilm));
    }
}