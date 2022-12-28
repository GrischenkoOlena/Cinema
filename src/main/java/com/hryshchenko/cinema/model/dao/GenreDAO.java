package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.GenreQueryBuilder;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.entity.Genre;

import java.sql.SQLException;
import java.util.List;

public class GenreDAO extends AbstractDAO<Integer, Genre>{

    private final QueryBuilder<Genre> genreQueryBuilder = new GenreQueryBuilder();

    @Override
    public List<Genre> findAll() throws DAOException {
        List<Genre> genres;
        try {
            genres = genreQueryBuilder.executeAndReturnList(connection, Query.GET_ALL_GENRES, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all genres", e);
        }
        return genres;
    }

    @Override
    public Genre findEntityByKey(Integer id) throws DAOException {
        Genre genre;
        try {
            genre = genreQueryBuilder.executeAndReturnValue(connection, Query.GET_GENRE_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find genre by id", e);
        }
        return genre;
    }

    @Override
    public boolean delete(Genre genre) throws DAOException {
        boolean result;
        try {
            result = genreQueryBuilder.execute(connection, Query.DELETE_GENRE, genre.getId());
        } catch (SQLException e){
            throw new DAOException("problem in delete genre", e);
        }
        return result;
    }

    @Override
    public boolean create(Genre genre) throws DAOException {
        boolean result;
        try {
            result = genreQueryBuilder.execute(connection, Query.CREATE_GENRE, genre.getGenre());
        } catch (SQLException e){
            throw new DAOException("problem in create genre", e);
        }
        return result;
    }

    @Override
    public boolean update(Genre genre) throws DAOException {
        boolean result;
        try {
            result = genreQueryBuilder.execute(connection, Query.UPDATE_GENRE, genre.getGenre(), genre.getId());
        } catch (SQLException e){
            throw new DAOException("problem in update genre", e);
        }
        return result;
    }
}
