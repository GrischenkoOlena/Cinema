package com.hryshchenko.cinema.model.dao;

import com.hryshchenko.cinema.constant.Query;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.builder.CategoryQueryBuilder;
import com.hryshchenko.cinema.model.builder.QueryBuilder;
import com.hryshchenko.cinema.model.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends AbstractDAO <Integer, Category> {
    private final QueryBuilder<Category> categoryQueryBuilder = new CategoryQueryBuilder();

    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> categories;
        try {
            categories = categoryQueryBuilder.executeAndReturnList(connection, Query.GET_ALL_CATEGORIES, 0,10);
        } catch (SQLException e){
            throw new DAOException("problem in find all category", e);
        }
        return categories;
    }

    @Override
    public Category findEntityByKey(Integer id) throws DAOException {
        Category category;
        try {
            category = categoryQueryBuilder.executeAndReturnValue(connection, Query.GET_CATEGORY_BY_ID, id);
        } catch (SQLException e){
            throw new DAOException("problem in find category by id", e);
        }
        return category;
    }

    @Override
    public boolean delete(Category category) throws DAOException {
        boolean result;
        try {
            result = categoryQueryBuilder.execute(connection, Query.DELETE_CATEGORY, category.getId());
        } catch (SQLException e){
            throw new DAOException("problem in delete category", e);
        }
        return result;
    }

    @Override
    public boolean create(Category category) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(category.getCategory());
            params.add(category.getPrice());
            result = categoryQueryBuilder.execute(connection, Query.CREATE_CATEGORY, params.toArray());
        } catch (SQLException e){
            throw new DAOException("problem in create category", e);
        }
        return result;
    }

    @Override
    public boolean update(Category category) throws DAOException {
        boolean result;
        try {
            List<Object> params = new ArrayList<>();
            params.add(category.getPrice());
            params.add(category.getId());
            result = categoryQueryBuilder.execute(connection, Query.UPDATE_CATEGORY, params);
        } catch (SQLException e){
            throw new DAOException("problem in update category", e);
        }
        return result;
    }
}
