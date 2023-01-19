package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.QueryExecutor;
import com.hryshchenko.cinema.model.entity.ScreeningView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hryshchenko.cinema.constant.FieldName.*;

public class ScreeningViewDAO extends AbstractDAO <Long, ScreeningView> {
    ScreeningViewQueryExecutor screeningViewQueryBuilder = new ScreeningViewQueryExecutor();

    @Override
    public List<ScreeningView> findAll() throws DAOException {
        List<ScreeningView> screenings;
        String query = Query.GET_ALL_SCREENING_VIEW.replace("orderField", "film_date");
        try {
            screenings = screeningViewQueryBuilder.executeAndReturnList(connection, query, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all screenings from view", e);
        }
        return screenings;
    }

    public int findCountScreeningViews() throws DAOException {
        int result;
        try {
            result = screeningViewQueryBuilder.executeAndReturnAggregate(connection,Query.COUNT_SCREENING_VIEW);
        } catch (SQLException e){
            throw new DAOException("problem in find count of screenings from view", e);
        }
        return result;
    }

    public List<ScreeningView> findPageScreeningViews(String order, long begin, long amount) throws DAOException {
        List<ScreeningView> screenings;
        String query = Query.GET_ALL_SCREENING_VIEW.replace("orderField", order);
        try {
            screenings = screeningViewQueryBuilder.executeAndReturnList(connection, query, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find page screenings from view", e);
        }
        return screenings;
    }

    public int findCountAvailableScreeningViews(LocalDate filmDate) throws DAOException {
        int result;
        try {
            result = screeningViewQueryBuilder.executeAndReturnAggregate(connection,
                                    Query.COUNT_SCREENING_VIEWS_BY_AVAILABLE, filmDate);
        } catch (SQLException e){
            throw new DAOException("problem in find count of available screenings from view", e);
        }
        return result;
    }

    public List<ScreeningView> findPageAvailableScreeningViews
                                (LocalDate filmDate, String order, long begin, long amount) throws DAOException {
        List<ScreeningView> screenings;
        String query = Query.GET_SCREENING_VIEWS_BY_AVAILABLE.replace("orderField", order);
        try {
            screenings = screeningViewQueryBuilder
                    .executeAndReturnList(connection, query, filmDate, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find page available screenings from view", e);
        }
        return screenings;
    }

    @Override
    public Optional<ScreeningView> findEntityByKey(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public boolean create(ScreeningView screeningView) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(ScreeningView screeningView) throws DAOException {
        return false;
    }

    @Override
    public boolean update(ScreeningView screeningView) throws DAOException {
        return false;
    }


    private class ScreeningViewQueryExecutor extends QueryExecutor<ScreeningView> {
        @Override
        public ScreeningView getResult(ResultSet rs) throws SQLException {
            ScreeningView screening = new ScreeningView();
            while (rs.next()){
                screening.setId(rs.getInt(VIEW_SCREENING_ID));
                screening.setFilmTitle(rs.getString(VIEW_FILM_TITLE));
                screening.setFilmDate(rs.getDate(VIEW_FILM_DATE).toLocalDate());
                screening.setTimeBegin(rs.getTime(VIEW_TIME_BEGIN).toLocalTime());
                screening.setState(rs.getString(VIEW_STATE));
                screening.setFreePlaces(rs.getInt(VIEW_AMOUNT_FREE_SEATS));
            }
            return screening;
        }

        @Override
        public List<ScreeningView> getListOfResult(ResultSet rs) throws SQLException {
            List<ScreeningView> screenings = new ArrayList<>();
            while (rs.next()){
                ScreeningView screening = new ScreeningView();
                screening.setId(rs.getInt(VIEW_SCREENING_ID));
                screening.setFilmTitle(rs.getString(VIEW_FILM_TITLE));
                screening.setFilmDate(rs.getDate(VIEW_FILM_DATE).toLocalDate());
                screening.setTimeBegin(rs.getTime(VIEW_TIME_BEGIN).toLocalTime());
                screening.setState(rs.getString(VIEW_STATE));
                screening.setFreePlaces(rs.getInt(VIEW_AMOUNT_FREE_SEATS));
                screenings.add(screening);
            }
            return screenings;
        }
    }

}
