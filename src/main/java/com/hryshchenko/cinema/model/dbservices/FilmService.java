package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.FilmDAO;
import com.hryshchenko.cinema.model.entity.Film;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class FilmService implements ICinemaService{
    private final FilmDAO filmDAO;

    public FilmService() {
        this.filmDAO = new FilmDAO();
    }
    public Optional<Film> getFilmById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        filmDAO.setConnection(conn);
        Optional<Film> film = filmDAO.findEntityByKey(id);
        dbManager.closeConnection(conn);
        return film;
    }

    public List<Film> getFilmsPage(String order, long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        filmDAO.setConnection(conn);
        List<Film> films = filmDAO.findPageFilms(order, begin, amount);
        dbManager.closeConnection(conn);
        return films;
    }

    public long getCountFilms() throws DAOException {
        Connection conn = dbManager.getConnection();
        filmDAO.setConnection(conn);
        long count = filmDAO.findCountFilms();
        dbManager.closeConnection(conn);
        return count;
    }
}
