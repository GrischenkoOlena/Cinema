package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperFilmTest {
    MapperFilm mapper = new MapperFilm();
    Film testFilm = new Film();
    FilmDTO testDTO;


    @BeforeEach
    void setUp() {
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
                .genre(new GenreDTO(1L, "historical"))
                .duration(180).build();
    }

    @Test
    void getDTOTest() {

    }

    @Test
    void getListDTO() {
    }

    @Test
    void getFilm() throws MapperException {
        assertEquals(testFilm, mapper.getFilm(testDTO));
    }
}