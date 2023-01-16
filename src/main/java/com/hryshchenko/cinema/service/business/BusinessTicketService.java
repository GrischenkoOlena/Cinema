package com.hryshchenko.cinema.service.business;

import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.NotEnoughMoneyException;
import com.hryshchenko.cinema.exception.SeatHasSoldException;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.model.dbservices.TicketService;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.TicketSeat;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.mapper.MapperSeat;
import com.hryshchenko.cinema.service.mapper.MapperTicket;
import com.hryshchenko.cinema.service.mapper.MapperUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusinessTicketService {

    private static final Lock MUTEX = new ReentrantLock();
    public boolean buyTicket(TicketDTO ticketDTO) throws NotEnoughMoneyException, SeatHasSoldException {
        double cost = 0;
        for(SeatDTO temp : ticketDTO.getSeats()){
            cost += temp.getCategory().getPrice();
        }

        Ticket ticket = new MapperTicket().getTicket(ticketDTO);
        User user = new MapperUser().getUser(ticketDTO.getUser());
        if(user.getBalance() > cost){
            user.setBalance(user.getBalance() - cost);
        } else {
            throw new NotEnoughMoneyException("you don't have enough money to buy ticket");
        }
        List<TicketSeat> ticketHasSeats = new ArrayList<>();
        for(SeatDTO seatDTO : ticketDTO.getSeats()){
            TicketSeat ticketSeat = new TicketSeat();
            ticketSeat.setSeatId((int) seatDTO.getId());
            ticketHasSeats.add(ticketSeat);
        }
        List<Seat> seats = new MapperSeat().getListSeat(ticketDTO.getSeats());

        TicketService ticketService = AppContext.getInstance().getTicketService();
        SeatService seatService = AppContext.getInstance().getSeatService();
        boolean result;
        try {
            MUTEX.lock();
            List<Seat> freeSeats = seatService.getFreeSeatByScreening(ticket.getScreeningId());
            for(Seat seat : seats){
                if(!freeSeats.contains(seat)) {
                    throw new SeatHasSoldException("unfortunately place is sold");
                }
            }
            result = ticketService.createTicket(ticket, ticketHasSeats, user);
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        } finally {
            MUTEX.unlock();
        }
        return result;
    }
}
