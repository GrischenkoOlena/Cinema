package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.FilmQueryBuilder;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.entity.Film;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO extends AbstractDAO <Long, Film>{
    private final QueryBuilder<Film> filmQueryBuilder = new FilmQueryBuilder();

    @Override
    public List<Film> findAll() throws DAOException {
        List<Film> films;
        try {
            films = filmQueryBuilder.executeAndReturnList(connection, Query.GET_ALL_FILMS, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all films", e);
        }
        return films;
    }

    @Override
    public Film findEntityByKey(Long id) throws DAOException {
        Film film;
        try {
            film = filmQueryBuilder.executeAndReturnValue(connection, Query.GET_FILM_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find film by id", e);
        }
        return film;
    }

    @Override
    public boolean delete(Film film) throws DAOException {
        boolean result;
        try {
            result = filmQueryBuilder.execute(connection, Query.DELETE_FILM, film.getId());
        } catch (SQLException e){
            throw new DAOException("problem in delete film", e);
        }
        return result;
    }

    @Override
    public boolean create(Film film) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(film.getTitle());
            params.add(film.getDirector());
            params.add(film.getCast());
            params.add(film.getDescription());
            params.add(film.getGenreID());
            params.add(film.getDuration());

            result = filmQueryBuilder.execute(connection, Query.CREATE_FILM, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in create film", e);
        }
        return result;
    }

    @Override
    public boolean update(Film film) throws DAOException {
        return false;
    }

    public Film findFilmByGenre(String genre) throws DAOException {
        Film film;
        try {
            film = filmQueryBuilder.executeAndReturnValue(connection, Query.GET_FILM_BY_GENRE, genre);
        } catch (SQLException e){
            throw new DAOException("problem in find film by genre", e);
        }
        return film;
    }
}
