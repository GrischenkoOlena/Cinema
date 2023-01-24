package com.hryshchenko.cinema.controller.commands.admin;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class AddFilmCommandTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    FilmService service;

    @InjectMocks
    AddFilmCommand command;

    @BeforeEach
    public void setUp(){
        Mockito.when(req.getParameter("title")).thenReturn("Avatar");
        Mockito.when(req.getParameter("director")).thenReturn("Cameron");
        Mockito.when(req.getParameter("cast")).thenReturn("Samuil");
        Mockito.when(req.getParameter("description")).thenReturn("This new film");
        Mockito.when(req.getParameter("genre")).thenReturn("1");
        Mockito.when(req.getParameter("duration")).thenReturn("120");
    }

    @Test
    public void executeTest() throws DAOException {
        Mockito.when(service.createFilm(Mockito.any())).thenReturn(true);
        command.execute(req, resp);
        Mockito.verify(service, Mockito.times(1)).createFilm(Mockito.any());
    }

}