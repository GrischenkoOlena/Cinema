package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.model.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperTicketTest {
    MapperTicket mapper = new MapperTicket();
    Ticket testTicket;
    TicketDTO testDTO;

    @BeforeEach
    void setUp() {
        testTicket = new Ticket(1L, 0, 1L, 2);

        testDTO = new TicketDTO();
        testDTO.setId(1L);
        testDTO.setScreening(new ScreeningDTO());
        testDTO.setUser(new UserDTO.UserDTOBuilder(1L).build());
        testDTO.setTicketCount(2);

    }

    @Test
    void getDTO() {
    }

    @Test
    void getListDTO() {
    }

    @Test
    void getTicket() {
        assertEquals(testTicket, mapper.getTicket(testDTO));
    }
}