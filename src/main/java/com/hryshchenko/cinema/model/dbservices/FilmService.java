package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.FilmDAO;
import com.hryshchenko.cinema.model.entity.Film;

import java.sql.Connection;

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
}
