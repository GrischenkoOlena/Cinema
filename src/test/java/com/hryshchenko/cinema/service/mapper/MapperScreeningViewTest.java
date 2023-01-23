package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.ScreeningView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperScreeningViewTest {
    MapperScreeningView mapper = new MapperScreeningView();
    ScreeningView testView;
    ScreeningViewDTO testDTO;

    @BeforeEach
    void setUp() {
        testView = new ScreeningView(1L, "Avatar", LocalDate.now(), LocalTime.MIDNIGHT, "active", 10);

        testDTO = new ScreeningViewDTO();
        testDTO.setId(1L);
        testDTO.setFilmTitle("Avatar");
        testDTO.setFilmDate(LocalDate.now());
        testDTO.setTimeBegin(LocalTime.MIDNIGHT);
        testDTO.setState("active");
        testDTO.setFreePlaces(10);
    }

    @Test
    void getDTO() throws MapperException {
        assertEquals(testDTO, mapper.getDTO(testView));
    }

    @Test
    void getListDTO() throws MapperException {
        List<ScreeningViewDTO> dtoList = new ArrayList<>();
        dtoList.add(testDTO);

        List<ScreeningView> entityList = new ArrayList<>();
        entityList.add(testView);

        assertEquals(dtoList, mapper.getListDTO(entityList));
    }
}