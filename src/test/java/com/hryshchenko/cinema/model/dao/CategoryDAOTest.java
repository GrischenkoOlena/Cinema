package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.executor.CategoryQueryExecutor;
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
class CategoryDAOTest {
    @Mock
    CategoryQueryExecutor queryExecutor;

    @InjectMocks
    CategoryDAO dao;
    Category testCategory;
    List<Category> testList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testCategory = new Category(1L, "premium", 150.0);
        testList.add(testCategory);
    }

    @Test
    void findAllTest() throws DAOException, SQLException {
        Mockito.when(queryExecutor
                        .executeAndReturnList(Mockito.any(), Mockito.anyString(),
                                Mockito.anyInt(), Mockito.anyInt()))
                        .thenReturn(testList);
        assertEquals(testList, dao.findAll());
        assertEquals(ArrayList.class, dao.findAll().getClass());
    }

    @Test
    void findEntityByKeyTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor
                        .executeAndReturnValue(Mockito.any(), Mockito.anyString(), Mockito.anyInt()))
                        .thenReturn(testCategory);
        assertTrue(dao.findEntityByKey(1).isPresent());
        assertEquals(testCategory, dao.findEntityByKey(1).get());
    }

    @Test
    void deleteTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(true);
        assertTrue(dao.delete(testCategory));
    }

    @Test
    void createTest() throws SQLException, DAOException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.create(testCategory));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.create(testCategory));
    }

    @Test
    void updateTest() throws DAOException, SQLException {
        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenReturn(true);
        assertTrue(dao.update(testCategory));

        Mockito.when(queryExecutor.execute(Mockito.any(), Mockito.anyString(), ArgumentMatchers.any()))
                .thenThrow(SQLException.class);
        assertThrows(DAOException.class, ()->dao.update(testCategory));
    }
}