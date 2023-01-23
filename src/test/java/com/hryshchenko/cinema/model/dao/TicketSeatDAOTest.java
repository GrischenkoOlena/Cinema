package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.TicketSeat;
import com.hryshchenko.cinema.model.executor.TicketSeatQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketSeatDAOTest {
    @Mock
    TicketSeatQueryExecutor queryExecutor;

    @InjectMocks
    TicketSeatDAO dao;

    TicketSeat tesTicketSeat;

    @BeforeEach
    public void setUp(){
        tesTicketSeat = new TicketSeat(1L, 1, 1);
    }


    @Test
    void createTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.create(tesTicketSeat));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.create(tesTicketSeat));
    }
}