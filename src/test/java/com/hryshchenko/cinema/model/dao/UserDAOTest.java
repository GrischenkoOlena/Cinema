package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.model.executor.UserQueryExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDAOTest {
    @Mock
    UserQueryExecutor queryExecutor;

    @InjectMocks
    UserDAO dao;

    User testUser;
    List<User> testList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setLogin("gena@i.ua");
        testUser.setPassword("1234");
        testUser.setName("Genadii");
        testUser.setBalance(150.0);
        testUser.setRole(UserRole.CLIENT);

        testList.add(testUser);
    }

    @Test
    void findAllTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                        .thenReturn(testList);
        assertEquals(testList, dao.findAll());
        assertEquals(ArrayList.class, dao.findAll().getClass());
    }

    @Test
    void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyString()))
                        .thenReturn(testUser);
        assertTrue(dao.findEntityByKey("gena@i.ua").isPresent());
        assertEquals(testUser, dao.findEntityByKey("gena@i.ua").get());
    }

    @Test
    void createTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(dao.create(testUser));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()-> dao.create(testUser));
    }

    @Test
    void deleteTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyString()))
                        .thenReturn(true);
        assertTrue(dao.delete(testUser));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.delete(testUser));
    }

    @Test
    void updateTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenReturn(true);
        assertTrue(dao.update(testUser));

        Mockito.when(queryExecutor
                        .execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()-> dao.update(testUser));
    }

    @Test
    void findCountUsersTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenReturn(5);
        assertEquals(5L, dao.findCountUsers());

        Mockito.when(queryExecutor
                        .executeAndReturnAggregate(Mockito.any(), Mockito.anyString()))
                        .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.findCountUsers());
    }

    @Test
    void findPageUsersTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
                        .thenReturn(testList);
        assertEquals(testList, dao.findPageUsers("test order", 1L, 5L));
        assertEquals(ArrayList.class, dao.findPageUsers("test order", 1L, 5L).getClass());
    }

    @Test
    void findUserByIdTest() throws SQLException, DAOException{
        Mockito.when(queryExecutor
                        .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                        .thenReturn(testUser);
        assertTrue(dao.findUserById(1L).isPresent());
        assertEquals(testUser, dao.findUserById(1L).get());
    }
}