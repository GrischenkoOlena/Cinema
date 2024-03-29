package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * Genre entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class GenreQueryExecutor extends QueryExecutor<Genre> {
    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.Genre}
     */
    @Override
    public Genre getResult(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        while (rs.next()){
            genre.setId(rs.getInt(GENRE_ID));
            genre.setGenre(rs.getString(GENRE_TITLE));
        }
        return genre;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Genre}
     */
    @Override
    public List<Genre> getListOfResult(ResultSet rs) throws SQLException {
        List <Genre> genres = new ArrayList<>();
        while (rs.next()){
            Genre genre = new Genre();
            genre.setId(rs.getInt(GENRE_ID));
            genre.setGenre(rs.getString(GENRE_TITLE));
            genres.add(genre);
        }

        return genres;
    }
}
