package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.model.entity.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperSeatTest {
    MapperSeat mapper = new MapperSeat();
    Seat testSeat;
    SeatDTO testDTO;

    @BeforeEach
    void setUp() {
        testSeat = new Seat();
        testSeat.setId(1L);
        testSeat.setPlace(1);
        testSeat.setLine(1);
        testSeat.setCategoryId(1);

        testDTO = new SeatDTO();
        testDTO.setId(1L);
        testDTO.setPlace(1);
        testDTO.setLine(1);
        testDTO.setCategory(new CategoryDTO(1, "premium", 150.0));
    }

    @Test
    void getDTO() {
    }

    @Test
    void getListDTO() {
    }

    @Test
    void getSeat() {
        assertEquals(testSeat, mapper.getSeat(testDTO));
    }

    @Test
    void getListSeat() {
        List<SeatDTO> dtoList = new ArrayList<>();
        dtoList.add(testDTO);

        List<Seat> entityList = new ArrayList<>();
        entityList.add(testSeat);

        assertEquals(entityList, mapper.getListSeat(dtoList));
    }
}