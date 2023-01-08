package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.ISimpleDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.entity.Entity;
import java.util.List;

public interface IMapperService <T extends Entity, G extends ISimpleDTO>{
    G getDTO(T entity) throws MapperException, DAOException;
    List<G> getListDTO(List<T> entities) throws MapperException;
}
