package com.hryshchenko.cinema.model.builder;

import com.hryshchenko.cinema.model.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

public class GenreQueryBuilder extends QueryBuilder <Genre> {
    @Override
    public Genre getResult(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        while (rs.next()){
            genre.setId(rs.getInt(GENRE_ID));
            genre.setGenre(rs.getString(GENRE_TITLE));
        }
        return genre;
    }

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
