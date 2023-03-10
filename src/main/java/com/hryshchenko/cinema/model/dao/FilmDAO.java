package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.executor.FilmQueryExecutor;
import com.hryshchenko.cinema.model.executor.QueryExecutor;
import com.hryshchenko.cinema.model.entity.Film;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmDAO extends AbstractDAO <Long, Film>{
    private QueryExecutor<Film> filmQueryExecutor = new FilmQueryExecutor();

    @Override
    public List<Film> findAll() throws DAOException {
        List<Film> films;
        String query = Query.GET_ALL_FILMS.replace("orderField", "");
        try {
            films = filmQueryExecutor.executeAndReturnList(connection, query, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all films", e);
        }
        return films;
    }

    @Override
    public Optional<Film> findEntityByKey(Long id) throws DAOException {
        Film film;
        try {
            film = filmQueryExecutor.executeAndReturnValue(connection, Query.GET_FILM_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find film by id", e);
        }
        return Optional.ofNullable(film);
    }

    @Override
    public boolean delete(Film film) throws DAOException {
        boolean result;
        try {
            result = filmQueryExecutor.execute(connection, Query.DELETE_FILM, film.getId());
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

            result = filmQueryExecutor.execute(connection, Query.CREATE_FILM, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in create film", e);
        }
        return result;
    }

    @Override
    public boolean update(Film film) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(film.getTitle());
            params.add(film.getDirector());
            params.add(film.getCast());
            params.add(film.getDescription());
            params.add(film.getDuration());
            params.add(film.getId());

            result = filmQueryExecutor.execute(connection, Query.UPDATE_FILM, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in update film", e);
        }
        return result;
    }

    public List<Film> findFilmsByGenre(long genreId) throws DAOException {
        List<Film> films;
        String query = Query.GET_FILM_BY_GENRE.replace("orderField", "");
        try {
            films = filmQueryExecutor.executeAndReturnList(connection, query, genreId);
        } catch (SQLException e){
            throw new DAOException("problem in find film by genre", e);
        }
        return films;
    }

    public long findCountFilms() throws DAOException {
        long result;
        try {
            result = filmQueryExecutor.executeAndReturnAggregate(connection,Query.COUNT_FILM);
        } catch (SQLException e){
            throw new DAOException("problem in find count of movies", e);
        }
        return result;
    }

    public List<Film> findPageFilms(String order, long begin, long amount) throws DAOException {
        List<Film> films;
        String query = Query.GET_ALL_FILMS.replace("orderField", order);
        try {
            films = filmQueryExecutor.executeAndReturnList(connection, query,begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find movies by page", e);
        }
        return films;
    }

    public long findCountFilmsByGenre(long genreId) throws DAOException {
        long result;
        try {
            result = filmQueryExecutor.executeAndReturnAggregate(connection,Query.COUNT_FILM_BY_GENRE, genreId);
        } catch (SQLException e){
            throw new DAOException("problem in find count of movies by genre", e);
        }
        return result;
    }

    public List<Film> findPageFilmsByGenre(long genreId, String order, long begin, long amount) throws DAOException {
        List<Film> films;
        String query = Query.GET_FILM_BY_GENRE.replace("orderField", order);
        try {
            films = filmQueryExecutor.executeAndReturnList(connection, query, genreId, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find page movies by genre", e);
        }
        return films;
    }
}
