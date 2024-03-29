package com.hryshchenko.cinema.model.executor;

import com.hryshchenko.cinema.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hryshchenko.cinema.constant.FieldName.*;

/**
 * Category entity query executor.
 *
 * @author Olena Hryshchenko.
 */
public class CategoryQueryExecutor extends QueryExecutor<Category> {

    /**
     * @return the {@link com.hryshchenko.cinema.model.entity.Category}
     */
    @Override
    public Category getResult(ResultSet rs) throws SQLException {
        Category category = new Category();
        while (rs.next()){
            category.setId(rs.getInt(CATEGORY_ID));
            category.setCategory(rs.getString(CATEGORY_TITLE));
            category.setPrice(rs.getDouble(CATEGORY_PRICE));
        }
        return category;
    }

    /**
     * @return the list of {@link com.hryshchenko.cinema.model.entity.Category}
     */
    @Override
    public List<Category> getListOfResult(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()){
            Category category = new Category();
            category.setId(rs.getInt(CATEGORY_ID));
            category.setCategory(rs.getString(CATEGORY_TITLE));
            category.setPrice(rs.getDouble(CATEGORY_PRICE));
            categories.add(category);
        }
        return categories;
    }
}
