package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.StatePlace;
import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.CategoryDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.CategoryService;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.model.entity.Category;
import com.hryshchenko.cinema.model.entity.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapperSeat implements IMapperService<Seat, SeatDTO> {
    private SeatService seatService = AppContext.getInstance().getSeatService();
    private CategoryService categoryService = AppContext.getInstance().getCategoryService();
    private IMapperService<Category, CategoryDTO> mapperService = new MapperCategory();
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

    public Seat getSeat(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setId(seatDTO.getId());
        seat.setLine(seatDTO.getLine());
        seat.setPlace(seatDTO.getPlace());
        seat.setCategoryId(seatDTO.getCategory().getId());
        return seat;
    }

    public List<Seat> getListSeat(List<SeatDTO> seatDTOList){
        List<Seat> seats = new ArrayList<>();
        for(SeatDTO seatDTO : seatDTOList){
            seats.add(getSeat(seatDTO));
        }
        return seats;
    }

    public SeatDTO getSeatDTObyID(int seatId) throws MapperException {
        try {
            Optional<Seat> seat = seatService.getSeatById(seatId);
            if(seat.isPresent()){
                return getDTO(seat.get());
            }
        } catch (DAOException e) {
            throw new MapperException("problem with mapping seat by Id", e);
        }
        throw new MapperException("such seatId is absent in BD");
    }
}
