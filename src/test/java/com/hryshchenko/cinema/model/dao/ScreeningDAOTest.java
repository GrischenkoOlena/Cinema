package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.executor.ScreeningQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScreeningDAOTest {
    @Mock
    ScreeningQueryExecutor queryExecutor;

    @InjectMocks
    ScreeningDAO dao;
    Screening testScreening;
    List<Screening> testList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testScreening = new Screening();
        testScreening.setId(1L);
        testScreening.setFilmId(1L);
        testScreening.setFilmDate(LocalDate.now());
        testScreening.setTimeBegin(LocalTime.of(10,0));
        testScreening.setStateId(1);

        testList.add(testScreening);

    }

    @Test
    void findAllTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(testList);
        assertEquals(testList, dao.findAll());
        assertEquals(ArrayList.class, dao.findAll().getClass());
    }

    @Test
    void findEntityByKey() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(testScreening);
        assertTrue(dao.findEntityByKey(1L).isPresent());
        assertEquals(testScreening, dao.findEntityByKey(1L).get());
    }

    @Test
    void deleteTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(dao.delete(testScreening));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.delete(testScreening));
    }

    @Test
    void createTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(dao.create(testScreening));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.create(testScreening));
    }

    @Test
    void update() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(dao.update(testScreening));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.update(testScreening));
    }

    @Test
    void findScreeningsByDateTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.any()))
                        .thenReturn(testList);
        assertEquals(testList, dao.findScreeningsByDate(LocalDate.now()));
        assertEquals(ArrayList.class, dao.findScreeningsByDate(LocalDate.now()).getClass());
    }

    @Test
    void findCountScreeningsTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenReturn(5);
        assertEquals(5L, dao.findCountScreenings());

        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findCountScreenings());
    }

    @Test
    void findPageScreeningsTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList, dao.findPageScreenings("test order", 1L, 5L));
        assertEquals(ArrayList.class, dao.findPageScreenings("test order", 1L, 5L).getClass());
    }

    @Test
    void findCountFreeSeatsByIdTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(5);
        assertEquals(5L, dao.findCountFreeSeatsById(1L));

        Mockito.when(queryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findCountFreeSeatsById(1L));
    }

    @Test
    void findCountAvailableScreeningsTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.any()))
                .thenReturn(5);
        assertEquals(5L, dao.findCountAvailableScreenings(LocalDate.now()));

        Mockito.when(queryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findCountAvailableScreenings(LocalDate.now()));
    }

    @Test
    void findPageAvailableScreeningsTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.any(),
                        Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList,
                dao.findPageAvailableScreenings(LocalDate.now(),"test order", 1L, 5L));
        assertEquals(ArrayList.class,
                dao.findPageAvailableScreenings(LocalDate.now(),"test order", 1L, 5L).getClass());
    }
}