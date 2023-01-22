package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Film;
import com.hryshchenko.cinema.model.executor.FilmQueryExecutor;
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
public class FilmDAOTest {
    @Mock
    FilmQueryExecutor filmQueryExecutor;

    @InjectMocks
    FilmDAO filmDAO;
    Film testFilm;
    List<Film> testList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        testFilm = new Film.FilmBuilder(1L)
                .title("Avatar")
                .director("Jame Cameron")
                .duration(120)
                .genreId(1)
                .build();
        testList.add(testFilm);
    }

    @Test
    public void findAllTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(testList);
        assertEquals(testList, filmDAO.findAll());
        assertEquals(ArrayList.class, filmDAO.findAll().getClass());
    }

    @Test
    public void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(testFilm);
        assertTrue(filmDAO.findEntityByKey(1L).isPresent());
        assertEquals(testFilm, filmDAO.findEntityByKey(1L).get());
    }

    @Test
    public void deleteTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                        .thenReturn(true);
        assertTrue(filmDAO.delete(testFilm));
    }

    @Test
    public void createTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(filmDAO.create(testFilm));

        Mockito.when(filmQueryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->filmDAO.create(testFilm));
    }

    @Test
    public void updateTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(filmDAO.update(testFilm));

        Mockito.when(filmQueryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->filmDAO.update(testFilm));
    }

    @Test
    public void findFilmsByGenreTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                        .thenReturn(testList);
        assertEquals(testList, filmDAO.findFilmsByGenre(1L));
        assertEquals(ArrayList.class, filmDAO.findFilmsByGenre(1L).getClass());
    }

    @Test
    public void findCountFilmsTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenReturn(5);
        assertEquals(5L, filmDAO.findCountFilms());

        Mockito.when(filmQueryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->filmDAO.findCountFilms());
    }

    @Test
    public void findPageFilmsTest() throws SQLException, DAOException{
        Mockito.when(filmQueryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList, filmDAO.findPageFilms("test order", 1L, 5L));
        assertEquals(ArrayList.class, filmDAO.findPageFilms("test order", 1L, 5L).getClass());
    }

    @Test
    public void findCountFilmsByGenreTest() throws SQLException, DAOException {
        Mockito.when(filmQueryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(5);
        assertEquals(5L, filmDAO.findCountFilmsByGenre(1L));

        Mockito.when(filmQueryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->filmDAO.findCountFilmsByGenre(1L));
    }

    @Test
    public void findPageFilmsByGenreTest() throws SQLException, DAOException{
        Mockito.when(filmQueryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(),
                        Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList, filmDAO.findPageFilmsByGenre(1L,"test order", 1L, 5L));
        assertEquals(ArrayList.class,
                filmDAO.findPageFilmsByGenre(1L,"test order", 1L, 5L).getClass());
    }

}
