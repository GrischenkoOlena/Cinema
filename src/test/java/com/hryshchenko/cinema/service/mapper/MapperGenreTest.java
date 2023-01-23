package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.GenreDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperGenreTest {
    MapperGenre mapper = new MapperGenre();
    Genre testGenre;
    GenreDTO testDTO;

    @BeforeEach
    void setUp() {
        testGenre = new Genre(1L, "comedy");
        testDTO = new GenreDTO(1L, "comedy");
    }

    @Test
    void getDTO() throws MapperException {
        assertEquals(testDTO, mapper.getDTO(testGenre));
    }

    @Test
    void getListDTO() throws MapperException {
        List<GenreDTO> dtoList = new ArrayList<>();
        dtoList.add(testDTO);

        List<Genre> entityList = new ArrayList<>();
        entityList.add(testGenre);

        assertEquals(dtoList, mapper.getListDTO(entityList));
    }
}