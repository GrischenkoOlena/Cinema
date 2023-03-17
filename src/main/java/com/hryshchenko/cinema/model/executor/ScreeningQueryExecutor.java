package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Screening;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * Screening entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class ScreeningQueryExecutor extends QueryExecutor<Screening> {
    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.Screening}
     */
    @Override
    public Screening getResult(ResultSet rs) throws SQLException {
        Screening screening = new Screening();
        while (rs.next()){
            screening.setId(rs.getInt(SCREENING_ID));
            screening.setFilmId(rs.getInt(SCREENING_FILM_ID));
            screening.setFilmDate(rs.getDate(SCREENING_FILM_DATE).toLocalDate());
            screening.setTimeBegin(rs.getTime(SCREENING_TIME_BEGIN).toLocalTime());
            screening.setStateId(rs.getInt(SCREENING_STATE_ID));
        }
        return screening;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Screening}
     */
    @Override
    public List<Screening> getListOfResult(ResultSet rs) throws SQLException {
        List<Screening> screenings = new ArrayList<>();
        while (rs.next()){
            Screening screening = new Screening();
            screening.setId(rs.getInt(SCREENING_ID));
            screening.setFilmId(rs.getInt(SCREENING_FILM_ID));
            screening.setFilmDate(rs.getDate(SCREENING_FILM_DATE).toLocalDate());
            screening.setTimeBegin(rs.getTime(SCREENING_TIME_BEGIN).toLocalTime());
            screening.setStateId(rs.getInt(SCREENING_STATE_ID));
            screenings.add(screening);
        }

        return screenings;
    }
}
