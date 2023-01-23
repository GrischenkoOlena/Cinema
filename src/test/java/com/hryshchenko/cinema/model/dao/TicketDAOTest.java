package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.executor.TicketQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketDAOTest {
    @Mock
    TicketQueryExecutor queryExecutor;

    @InjectMocks
    TicketDAO dao;
    Ticket testTicket;
    List<Ticket> testList = new ArrayList();

    @BeforeEach
    void setUp() {
        testTicket = new Ticket(1L, 1L, 1L, 2);
        testList.add(testTicket);
    }

    @Test
    void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyInt()))
                        .thenReturn(testTicket);
        assertTrue(dao.findEntityByKey(1).isPresent());
        assertEquals(testTicket, dao.findEntityByKey(1).get());
    }

    @Test
    void deleteTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(true);
        assertTrue(dao.delete(testTicket));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.delete(testTicket));
    }

    @Test
    void createTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.create(testTicket));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.create(testTicket));
    }

    @Test
    void updateTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyLong()))
                        .thenReturn(true);
        assertTrue(dao.update(testTicket));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyLong()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.update(testTicket));
    }

    @Test
    void findTicketsByUserTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.executeAndReturnList(Mockito.any(), Mockito.anyString(),Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList, dao.findTicketsByUser(1L));
        assertEquals(ArrayList.class, dao.findTicketsByUser(1L).getClass());
    }

    @Test
    void findCountTicketByUser() throws SQLException, DAOException{
        Mockito.when(queryExecutor.executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(5);
        assertEquals(5, dao.findCountTicketByUser(1L));
    }

    @Test
    void findPageTicketsTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(),
                        Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList, dao.findPageTickets("fake order",1L, 1L, 5L));
        assertEquals(ArrayList.class,
                dao.findPageTickets("fake order",1L, 1L, 5L).getClass());
    }

    @Test
    void findCountTicketByUserDate() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                .executeAndReturnAggregate(Mockito.any(), Mockito.anyString(), Mockito.anyLong(), Mockito.any()))
                .thenReturn(5);
        assertEquals(5, dao.findCountTicketByUserDate(1L, LocalDate.now()));
    }

    @Test
    void findPageTicketsByUserDate() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong(),
                        Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(testList);
        assertEquals(testList,
                dao.findPageTicketsByUserDate("fake order", 1L, LocalDate.now(), 1L, 5L));
        assertEquals(ArrayList.class,
                dao.findPageTicketsByUserDate("fake order", 1L, LocalDate.now(), 1L, 5L)
                        .getClass());
    }

    @Test
    void findNextAutoIncrement() throws SQLException, DAOException{
        Mockito.when(queryExecutor.executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                .thenReturn(5);
        assertEquals(5, dao.findNextAutoIncrement());

        Mockito.when(queryExecutor.executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()-> dao.findNextAutoIncrement());

    }
}