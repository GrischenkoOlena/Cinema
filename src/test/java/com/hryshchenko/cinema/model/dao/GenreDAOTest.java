package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Genre;
import com.hryshchenko.cinema.model.executor.GenreQueryExecutor;
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
class GenreDAOTest {
    @Mock
    GenreQueryExecutor queryExecutor;

    @InjectMocks
    GenreDAO dao;
    Genre testGenre;
    List<Genre> testList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testGenre = new Genre(1, "fantastic");
        testList.add(testGenre);
    }

    @Test
    void findAllTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(),
                                Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(testList);
        assertEquals(testList, dao.findAll());
        assertEquals(ArrayList.class, dao.findAll().getClass());
    }

    @Test
    void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(testGenre);
        assertTrue(dao.findEntityByKey(1).isPresent());
        assertEquals(testGenre, dao.findEntityByKey(1).get());
    }

    @Test
    void deleteTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(true);
        assertTrue(dao.delete(testGenre));
    }

    @Test
    void createTest() throws DAOException, SQLException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.create(testGenre));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.create(testGenre));
    }

    @Test
    void updateTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.update(testGenre));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.update(testGenre));
    }
}