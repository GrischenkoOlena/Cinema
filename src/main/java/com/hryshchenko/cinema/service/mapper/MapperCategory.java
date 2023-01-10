package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class MapperCategory implements IMapperService<Category, CategoryDTO> {
    @Override
    public CategoryDTO getDTO(Category entity) throws MapperException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(entity.getId());
        categoryDTO.setCategory(entity.getCategory());
        categoryDTO.setPrice(entity.getPrice());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getListDTO(List<Category> entities) throws MapperException {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category : entities){
            categoryDTOList.add(getDTO(category));
        }
        return categoryDTOList;
    }
}
