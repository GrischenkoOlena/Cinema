package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.CategoryService;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperSeatTest {
    @Mock
    SeatService seatService;
    @Mock
    CategoryService categoryService;

    @InjectMocks
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
    void getDTO() throws MapperException, DAOException {
        Mockito.when(categoryService.getCategoryByID(Mockito.anyInt()))
                .thenReturn(Optional.of(new Category(1, "premium", 150.0)));
        assertEquals(testDTO, mapper.getDTO(testSeat));
    }
    @Test
    void getDTOWithException() throws DAOException {
        Mockito.when(categoryService.getCategoryByID(Mockito.anyInt())).thenThrow(DAOException.class);
        assertThrows(MapperException.class, ()-> mapper.getDTO(testSeat));
    }

    @Test
    void getDTOEmpty() throws DAOException {
        Mockito.when(categoryService.getCategoryByID(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(MapperException.class, ()-> mapper.getDTO(testSeat));
    }

    @Test
    void getListDTO() throws DAOException, MapperException {
        List<SeatDTO> dtoList = new ArrayList<>();
        dtoList.add(testDTO);

        List<Seat> entityList = new ArrayList<>();
        entityList.add(testSeat);

        Mockito.when(categoryService.getCategoryByID(Mockito.anyInt()))
                .thenReturn(Optional.of(new Category(1, "premium", 150.0)));

        assertEquals(dtoList, mapper.getListDTO(entityList));
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

    @Test
    void getSeatDTObyID() throws DAOException, MapperException {
        Mockito.when(categoryService.getCategoryByID(Mockito.anyInt()))
                .thenReturn(Optional.of(new Category(1, "premium", 150.0)));
        Mockito.when(seatService.getSeatById(Mockito.anyInt())).thenReturn(Optional.of(testSeat));
        assertEquals(testDTO, mapper.getSeatDTObyID(1));
    }

    @Test
    void getSeatDTObyIDWithException() throws DAOException {
        Mockito.when(seatService.getSeatById(Mockito.anyInt())).thenThrow(DAOException.class);
        assertThrows(MapperException.class, ()-> mapper.getSeatDTObyID(1));
    }

    @Test
    void getSeatDTObyIDEmpty() throws DAOException {
        Mockito.when(seatService.getSeatById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(MapperException.class, ()-> mapper.getSeatDTObyID(1));
    }
}