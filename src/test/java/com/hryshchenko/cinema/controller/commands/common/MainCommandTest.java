package com.hryshchenko.cinema.controller.commands.common;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.service.mapper.MapperScreening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MainCommandTest {
    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    ScreeningService service;

    @Mock
    MapperScreening mapper;

    @InjectMocks
    MainCommand command;

    @BeforeEach
    void setUp() throws DAOException, MapperException{
        Mockito.when(req.getParameter("scheduleDate")).thenReturn("25/01/2023");
        Mockito.when(service.getScreeningByDate(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(mapper.getListDTO(Mockito.any())).thenReturn(new ArrayList<>());
        command = new MainCommand(service, mapper);
    }

    @Test
    void execute()  {
        assertEquals(Path.MAIN, command.execute(req, resp));
    }
}