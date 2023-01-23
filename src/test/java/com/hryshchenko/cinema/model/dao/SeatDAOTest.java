package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.executor.SeatQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SeatDAOTest {
    @Mock
    SeatQueryExecutor queryExecutor;

    @InjectMocks
    SeatDAO dao;
    Seat testSeat;
    List<Seat> testList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testSeat = new Seat(1, 1, 1, 2);
        testList.add(testSeat);
    }

    @Test
    void findAllTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.executeAndReturnList(Mockito.any(), Mockito.anyString()))
                .thenReturn(testList);
        assertEquals(testList, dao.findAll());
        assertEquals(ArrayList.class, dao.findAll().getClass());
    }

    @Test
    void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyInt()))
                        .thenReturn(testSeat);
        assertTrue(dao.findEntityByKey(1).isPresent());
        assertEquals(testSeat, dao.findEntityByKey(1).get());
    }

    @Test
    void deleteTest() throws DAOException {
        assertFalse(dao.delete(testSeat));
    }

    @Test
    void createTest() throws DAOException {
        assertFalse(dao.create(testSeat));
    }

    @Test
    void updateTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyLong()))
                        .thenReturn(true);
        assertTrue(dao.update(testSeat));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(),  Mockito.anyInt(), Mockito.anyLong()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.update(testSeat));
    }

    @Test
    void findMaxRowTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenReturn(5);
        assertEquals(5L, dao.findMaxRow());

        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findMaxRow());
    }

    @Test
    void findMaxPlaceTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenReturn(5);
        assertEquals(5L, dao.findMaxPlace());

        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findMaxPlace());
    }

    @Test
    void getSeatByTicketTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                        .thenReturn(testList);
        assertEquals(testList, dao.getSeatByTicket(1L));
        assertEquals(ArrayList.class, dao.getSeatByTicket(1L).getClass());
    }
}