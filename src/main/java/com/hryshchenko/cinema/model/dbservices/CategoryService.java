package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dao.CategoryDAO;
import com.hryshchenko.cinema.model.entity.Category;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CategoryService extends CinemaService {
    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public Optional<Category> getCategoryByID(int id) throws DAOException {
        Connection conn = dbManager.getConnection();
        categoryDAO.setConnection(conn);
        Optional<Category> category = categoryDAO.findEntityByKey(id);
        dbManager.closeConnection(conn);
        return category;
    }

    public List<Category> getAllCategory() throws DAOException {
        Connection conn = dbManager.getConnection();
        categoryDAO.setConnection(conn);
        List<Category> category = categoryDAO.findAll();
        dbManager.closeConnection(conn);
        return category;
    }
}
