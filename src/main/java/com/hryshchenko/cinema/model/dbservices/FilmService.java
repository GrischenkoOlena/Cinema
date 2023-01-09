package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.FilmDAO;
import com.hryshchenko.cinema.model.entity.Film;

import java.sql.Connection;
import java.util.List;

public class FilmService implements ICinemaService{
    private final FilmDAO filmDAO;

    public FilmService() {
        this.filmDAO = new FilmDAO();
    }
    public Film getFilmById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        filmDAO.setConnection(conn);
        Film film = filmDAO.findEntityByKey(id);
        dbManager.closeConnection(conn);
        return film;
    }

    public List<Film> getFilmsPage(long begin, long amount) throws DAOException {
        Connection conn = dbManager.getConnection();
        filmDAO.setConnection(conn);
        List<Film> films = filmDAO.findPageFilms(begin, amount);
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
