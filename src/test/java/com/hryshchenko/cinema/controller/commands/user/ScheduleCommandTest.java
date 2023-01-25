package com.hryshchenko.cinema.controller.commands.user;

import com.hryshchenko.cinema.dto.ScreeningViewDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.ScreeningView;
import com.hryshchenko.cinema.service.Pagination;
import com.hryshchenko.cinema.service.mapper.IMapperService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ScheduleCommandTest {
    @Mock
    Pagination screeningsPagination;

    @Mock
    IMapperService<ScreeningView, ScreeningViewDTO> mapperScreening;

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @InjectMocks
    ScheduleCommand command;

    @BeforeEach
    public void setUp(){
        HttpSession session = mock(HttpSession.class);
        Mockito.when(req.getParameter("btnApplySort")).thenReturn("");
        Mockito.when(req.getParameter("page")).thenReturn("");
        Mockito.when(req.getParameter("order")).thenReturn("");
        Mockito.when(req.getSession()).thenReturn(session);
    }

    @Test
    void executeTest() throws DAOException, MapperException {
        Mockito.when(screeningsPagination
                .getFilterScreeningViewsPage(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(new ArrayList<>());
        Mockito.when(screeningsPagination.getCountFilterScreeningViewPages(Mockito.any()))
                .thenReturn(1L);
        Mockito.when(mapperScreening.getListDTO(Mockito.any()))
                .thenReturn(new ArrayList<>());

        command.execute(req, resp);
        Mockito.verify(screeningsPagination, Mockito.times(1))
                .getFilterScreeningViewsPage(Mockito.any(), Mockito.anyString(), Mockito.anyLong());
        Mockito.verify(screeningsPagination, Mockito.times(1))
                .getCountFilterScreeningViewPages(Mockito.any());
        Mockito.verify(mapperScreening, Mockito.times(1))
                .getListDTO(Mockito.any());

    }
}