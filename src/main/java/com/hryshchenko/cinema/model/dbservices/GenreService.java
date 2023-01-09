package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.GenreDAO;
import com.hryshchenko.cinema.model.entity.Genre;

import java.sql.Connection;
import java.util.List;

public class GenreService implements ICinemaService{
    private final GenreDAO genreDAO;

    public GenreService() {
        this.genreDAO = new GenreDAO();
    }
    public Genre getGenreById(long id) throws DAOException {
        Connection conn = dbManager.getConnection();
        genreDAO.setConnection(conn);
        Genre genre = genreDAO.findEntityByKey((int) id);
        dbManager.closeConnection(conn);
        return genre;
    }

    public List<Genre> getAllGenre() throws DAOException {
        Connection conn = dbManager.getConnection();
        genreDAO.setConnection(conn);
        List<Genre> genres = genreDAO.findAll();
        dbManager.closeConnection(conn);
        return genres;
    }
}
