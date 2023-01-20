package com.hryshchenko.cinema.model;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.FilmDAO;
import com.hryshchenko.cinema.model.dbservices.FilmService;
import com.hryshchenko.cinema.model.entity.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @Mock
    FilmDAO daoMock;

    private Film testFilm;
    List<Film> testList = new ArrayList<>();

    @InjectMocks
    FilmService cut;

    @BeforeEach
    public void setUp(){
        testFilm = new Film.FilmBuilder(1L)
                .title("Avatar")
                .director("Jame Cameron")
                .duration(120)
                .genreId(1)
                .build();
        testList.add(testFilm);

        daoMock = Mockito.mock(FilmDAO.class);
    }

    @Test
    public void getFilmByIdTest() throws DAOException {
        Mockito.when(daoMock.findEntityByKey(1L)).thenReturn(Optional.of(testFilm));
        assertTrue(cut.getFilmById(1L).isPresent());
        assertEquals(testFilm, cut.getFilmById(1L).get());
    }

}
