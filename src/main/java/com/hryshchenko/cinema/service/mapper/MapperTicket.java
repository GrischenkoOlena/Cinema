package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.ScreeningDTO;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.MapperException;
import com.hryshchenko.cinema.model.dbservices.ScreeningService;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.Screening;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MapperTicket implements IMapperService<Ticket, TicketDTO> {
    @Override
    public TicketDTO getDTO(Ticket entity) throws MapperException {
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setScreening(getScreeningDTO(entity.getScreeningId()));
        ticketDTO.setUser(getUserDTO(entity.getUserId()));

        ticketDTO.setTicketCount(entity.getTicketCount());
        ticketDTO.setSeats(getSeats(entity));
        return ticketDTO;
    }

    @Override
    public List<TicketDTO> getListDTO(List<Ticket> entities) throws MapperException {
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : entities){
            ticketDTOList.add(getDTO(ticket));
        }
        return ticketDTOList;
    }

    private ScreeningDTO getScreeningDTO(long screeningId) throws MapperException {
        ScreeningService screeningService = AppContext.getInstance().getScreeningService();
        IMapperService<Screening, ScreeningDTO> mapperService = new MapperScreening();
        try {
            Screening screening = screeningService.getScreeningById(screeningId);
            return mapperService.getDTO(screening);
        } catch (DAOException e) {
            throw new MapperException("problem with get screening from ticket");
        }
    }

    private UserDTO getUserDTO(int userId) throws MapperException {
        UserService userService = AppContext.getInstance().getUserService();
        IMapperService<User, UserDTO> mapperService = new MapperUser();
        try {
            User user = userService.getUserById(userId);
            return mapperService.getDTO(user);
        } catch (DAOException e) {
            throw new MapperException(e);
        }
    }

    private List<SeatDTO> getSeats(Ticket ticket) throws MapperException {
        SeatService seatService = AppContext.getInstance().getSeatService();
        try {
            List<Seat> allSeats = seatService.getSeatsByTicket(ticket);
            List<SeatDTO> seats = new ArrayList<>();
            for(Seat seat : allSeats){
                seats.add(SeatDTO.build(seat));
            }
            return seats;
        } catch (DAOException e) {
            throw new MapperException(e);
        }
    }
}
