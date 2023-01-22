package com.hryshchenko.cinema.model.executor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class QueryExecutorTest {
    @Mock
    Connection conn;
    @Mock
    PreparedStatement fakeSmt;

    FilmQueryExecutor executor = new FilmQueryExecutor();

    @BeforeEach
    public void setup() throws SQLException {
        Mockito.when(conn.prepareStatement("fake query")).thenReturn(fakeSmt);
    }

    @Test
    public void executeTest() throws SQLException {
        Mockito.when(fakeSmt.executeUpdate()).thenReturn(1);
        assertTrue(executor.execute(conn, "fake query"));
        Mockito.verify(fakeSmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void executeAndReturnValueTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Mockito.when(fakeSmt.executeQuery()).thenReturn(resultSet);
        executor.executeAndReturnValue(conn, "fake query");
        Mockito.verify(fakeSmt, Mockito.times(1)).executeQuery();
    }

    @Test
    public void executeAndReturnAggregateTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Mockito.when(fakeSmt.executeQuery()).thenReturn(resultSet);
        int testAggregate = executor.executeAndReturnAggregate(conn, "fake query");
        assertEquals(0, testAggregate);
        Mockito.verify(fakeSmt, Mockito.times(1)).executeQuery();
    }

    @Test
    public void executeAndReturnListTest() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Mockito.when(fakeSmt.executeQuery()).thenReturn(resultSet);
        assertEquals(ArrayList.class, executor.executeAndReturnList(conn, "fake query").getClass());
        Mockito.verify(fakeSmt, Mockito.times(1)).executeQuery();
    }
}
