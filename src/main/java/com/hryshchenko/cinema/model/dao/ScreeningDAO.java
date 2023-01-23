package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.executor.QueryExecutor;
import com.hryshchenko.cinema.model.executor.ScreeningQueryExecutor;
import com.hryshchenko.cinema.model.entity.Screening;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScreeningDAO extends AbstractDAO <Long, Screening> {

    private QueryExecutor<Screening> screeningQueryExecutor = new ScreeningQueryExecutor();
    @Override
    public List<Screening> findAll() throws DAOException {
        List<Screening> screenings;
        String query = Query.GET_ALL_SCREENINGS.replace("orderField", "film_date");
        try {
            screenings = screeningQueryExecutor.executeAndReturnList(connection, query, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all screenings", e);
        }
        return screenings;
    }

    @Override
    public Optional<Screening> findEntityByKey(Long id) throws DAOException {
        Screening screening;
        try {
            screening = screeningQueryExecutor.executeAndReturnValue(connection, Query.GET_SCREENING_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find screening by id", e);
        }
        return Optional.ofNullable(screening);
    }

    @Override
    public boolean delete(Screening screening) throws DAOException {
        boolean result;
        try {
            result = screeningQueryExecutor.execute(connection, Query.DELETE_SCREENING, screening.getId());
        } catch (SQLException e){
            throw new DAOException("problem in delete screening", e);
        }
        return result;
    }

    @Override
    public boolean create(Screening screening) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(screening.getFilmId());
            params.add(screening.getFilmDate());
            params.add(screening.getTimeBegin());
            params.add(screening.getStateId());

            result = screeningQueryExecutor.execute(connection, Query.CREATE_SCREENING, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in delete screening", e);
        }
        return result;
    }

    @Override
    public boolean update(Screening screening) throws DAOException {
        boolean result;
        try {
            result = screeningQueryExecutor.execute(connection,
                    Query.UPDATE_SCREENING, screening.getStateId(), screening.getId());
        } catch (SQLException e){
            throw new DAOException("problem in update screening", e);
        }
        return result;
    }

    public List<Screening> findScreeningsByDate(LocalDate date) throws DAOException {
        List<Screening> screenings;
        try {
            screenings = screeningQueryExecutor.executeAndReturnList(connection,
                    Query.GET_SCREENINGS_BY_DATE, date);
        } catch (SQLException e){
            throw new DAOException("problem in find screenings by date", e);
        }
        return screenings;
    }

    public long findCountScreenings() throws DAOException {
        long result;
        try {
            result = screeningQueryExecutor.executeAndReturnAggregate(connection,Query.COUNT_SCREENING);
        } catch (SQLException e){
            throw new DAOException("problem in find count of screenings", e);
        }
        return result;
    }
    public List<Screening> findPageScreenings(String order, long begin, long amount) throws DAOException {
        List<Screening> screenings;
        try {
            String query = Query.GET_ALL_SCREENINGS.replace("orderField", order);
            screenings = screeningQueryExecutor.executeAndReturnList(connection, query, begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find screenings by page", e);
        }
        return screenings;
    }

    public int findCountFreeSeatsById(long screeningId) throws DAOException {
        int result;
        try {
            result = screeningQueryExecutor.executeAndReturnAggregate(connection,
                            Query.GET_COUNT_FREE_SEAT_BY_SCREENING, screeningId);
        } catch (SQLException e) {
            throw new DAOException("problem with get count free seats", e);
        }
        return result;
    }

    public long findCountAvailableScreenings(LocalDate filmDate) throws DAOException {
        long result;
        try {
            result = screeningQueryExecutor.executeAndReturnAggregate(connection,
                    Query.COUNT_SCREENING_BY_AVAILABLE, filmDate);
        } catch (SQLException e){
            throw new DAOException("problem in find count of available screenings", e);
        }
        return result;
    }
    public List<Screening> findPageAvailableScreenings(LocalDate filmDate, String order, long begin, long amount)
                        throws DAOException {
        List<Screening> screenings;
        try {
            String query = Query.GET_SCREENINGS_BY_AVAILABLE.replace("orderField", order);
            screenings = screeningQueryExecutor.executeAndReturnList(connection, query, filmDate,begin-1, amount);
        } catch (SQLException e){
            throw new DAOException("problem in find available screenings by page", e);
        }
        return screenings;
    }
}
