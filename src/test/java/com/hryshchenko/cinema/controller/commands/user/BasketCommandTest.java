package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.service.mapper.MapperSeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BasketCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    HttpSession session;

    @Mock
    MapperSeat mapper;

    @InjectMocks
    BasketCommand command;

    SeatDTO seat = new SeatDTO();

    @BeforeEach
    void setUp() throws MapperException {
        Mockito.when(req.getSession()).thenReturn(session);

        seat.setId(1L);
        seat.setCategory(new CategoryDTO(1, "", 200.0));
        Mockito.when(mapper.getSeatDTObyID(1)).thenReturn(seat);

        Mockito.when(req.getParameter("placeId")).thenReturn("1");

        ScreeningDTO screening = new ScreeningDTO();
        screening.setId(1L);
        Mockito.when(session.getAttribute("screening")).thenReturn(screening);
    }

    @Test
    void executeUserTest() {
        Mockito.when(session.getAttribute("userRole")).thenReturn(UserRole.CLIENT);
        String result = command.execute(req, resp);
        assertEquals(Path.TICKER_BASKET, result);
    }

    @Test
    void executeAdminTest() {
        Mockito.when(session.getAttribute("userRole")).thenReturn(UserRole.ADMIN);
        String result = command.execute(req, resp);
        assertEquals(Path.COMMAND_REDIRECT, result);
    }
}