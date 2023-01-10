package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.StatePlace;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.CategoryService;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapperSeat implements IMapperService<Seat, SeatDTO> {
    @Override
    public SeatDTO getDTO(Seat entity) throws MapperException {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(entity.getId());
        seatDTO.setLine(entity.getLine());
        seatDTO.setPlace(entity.getPlace());
        seatDTO.setCategory(getCategoryById(entity.getCategoryId()));
        seatDTO.setState(StatePlace.FREE);
        return seatDTO;
    }

    @Override
    public List<SeatDTO> getListDTO(List<Seat> entities) throws MapperException {
        List<SeatDTO> seatDTOList = new ArrayList<>();
        for(Seat seat : entities){
            seatDTOList.add(getDTO(seat));
        }
        return seatDTOList;
    }

    private CategoryDTO getCategoryById(int id) throws MapperException {
        CategoryService categoryService = AppContext.getInstance().getCategoryService();
        IMapperService<Category, CategoryDTO> mapperService = new MapperCategory();

        try {
            Optional<Category> category = categoryService.getCategoryByID(id);
            if (category.isPresent()) {
                return (mapperService.getDTO(category.get()));
            }
        } catch (DAOException e) {
            throw new MapperException("problem with mapping seat", e);
        }
        throw new MapperException("such category is absent in BD");
    }
}
