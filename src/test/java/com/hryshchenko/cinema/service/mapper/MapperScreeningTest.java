package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.StateScreening;
import com.hryshchenko.cinema.dto.FilmDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.model.entity.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MapperScreeningTest {
    MapperScreening mapper = new MapperScreening();
    Screening testScreening;
    ScreeningDTO testScreeningDTO;

    @BeforeEach
    void setUp() {
        testScreening = new Screening(1L, 1L, LocalDate.now(), LocalTime.MIDNIGHT, 1);

        testScreeningDTO = new ScreeningDTO();
        testScreeningDTO.setId(1L);
        testScreeningDTO.setFilm(new FilmDTO.FilmDTOBuilder(1L).build());
        testScreeningDTO.setFilmDate(LocalDate.now());
        testScreeningDTO.setTimeBegin(LocalTime.MIDNIGHT);
        testScreeningDTO.setState(StateScreening.ACTIVE);
    }

    @Test
    void getDTO() {
    }

    @Test
    void getListDTO() {
    }

    @Test
    void getScreening() {
        assertEquals(testScreening, mapper.getScreening(testScreeningDTO));
    }
}