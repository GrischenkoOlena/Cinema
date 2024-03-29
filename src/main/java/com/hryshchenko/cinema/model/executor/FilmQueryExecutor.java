package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * Film entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class FilmQueryExecutor extends QueryExecutor<Film> {
    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.Film}
     */
    @Override
    public Film getResult(ResultSet rs) throws SQLException {
        Film film = null;
        while (rs.next()){
            film = new Film.FilmBuilder(rs.getInt(FILM_ID))
                    .title(rs.getString(FILM_TITLE))
                    .director(rs.getString(FILM_DIRECTOR))
                    .cast(rs.getString(FILM_CAST))
                    .description(rs.getString(FILM_DESCRIPTION))
                    .genreId(rs.getInt(FILM_GENRE_ID))
                    .duration(rs.getInt(FILM_DURATION))
                    .build();
        }
        return film;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Film}
     */
    @Override
    public List<Film> getListOfResult(ResultSet rs) throws SQLException {
        List<Film> films = new ArrayList<>();
        while (rs.next()){
            Film film = new Film.FilmBuilder(rs.getInt(FILM_ID))
                    .title(rs.getString(FILM_TITLE))
                    .director(rs.getString(FILM_DIRECTOR))
                    .cast(rs.getString(FILM_CAST))
                    .description(rs.getString(FILM_DESCRIPTION))
                    .genreId(rs.getInt(FILM_GENRE_ID))
                    .duration(rs.getInt(FILM_DURATION))
                    .build();
            films.add(film);
        }

        return films;
    }
}
