package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperCategoryTest {
    MapperCategory mapperCategory = new MapperCategory();
    Category category;
    CategoryDTO categoryDTO;

    @BeforeEach
    public void setUp(){
        category = new Category(1, "premium", 150.0);
        categoryDTO = new CategoryDTO(1, "premium", 150.0);
    }

    @Test
    public void getDTOTest() throws MapperException {
        assertEquals(categoryDTO, mapperCategory.getDTO(category));
    }

    @Test
    public void getDTOList() throws MapperException {
        List<CategoryDTO> dtoList = new ArrayList<>();
        dtoList.add(categoryDTO);

        List<Category> entityList = new ArrayList<>();
        entityList.add(category);

        assertEquals(dtoList, mapperCategory.getListDTO(entityList));
    }
}
