package com.hryshchenko.cinema.service.business;

import com.hryshchenko.cinema.context.AppContext;
import com.hryshchenko.cinema.dto.SeatDTO;
import com.hryshchenko.cinema.dto.TicketDTO;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.exception.NotEnoughMoneyException;
import com.hryshchenko.cinema.exception.SeatHasSoldException;
import com.hryshchenko.cinema.model.dao.TicketDAO;
import com.hryshchenko.cinema.model.dao.TicketSeatDAO;
import com.hryshchenko.cinema.model.dao.UserDAO;
import com.hryshchenko.cinema.model.dbservices.EntityTransaction;
import com.hryshchenko.cinema.model.dbservices.SeatService;
import com.hryshchenko.cinema.model.entity.Seat;
import com.hryshchenko.cinema.model.entity.Ticket;
import com.hryshchenko.cinema.model.entity.TicketSeat;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.service.mapper.MapperSeat;
import com.hryshchenko.cinema.service.mapper.MapperTicket;
import com.hryshchenko.cinema.service.mapper.MapperUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusinessTicketService {
    private static final Logger log = LogManager.getLogger();
    private static final Lock MUTEX = new ReentrantLock();
    public boolean buyTicket(TicketDTO ticketDTO) throws NotEnoughMoneyException, SeatHasSoldException {
        double cost = ticketDTO.getSeats().stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();

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

        SeatService seatService = AppContext.getInstance().getSeatService();
        boolean result;
        try {
            MUTEX.lock();
            List<Seat> freeSeats = seatService.getFreeSeatByScreening(ticket.getScreeningId());
            for(Seat seat : seats){
                if(!freeSeats.contains(seat)) {
                    throw new SeatHasSoldException("unfortunately place has sold");
                }
            }
            result = createTicket(ticket, ticketHasSeats, user);
        } catch (DAOException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            MUTEX.unlock();
        }
        return result;
    }

    private boolean createTicket(Ticket ticket, List<TicketSeat> seats, User user) throws DAOException{
        TicketDAO ticketDAO = new TicketDAO();
        UserDAO userDAO = new UserDAO();
        TicketSeatDAO ticketSeatDAO = new TicketSeatDAO();

        EntityTransaction entityTransaction = new EntityTransaction();

        entityTransaction.initTransaction(ticketDAO, userDAO, ticketSeatDAO);
        if (!ticketDAO.create(ticket)){
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
        }
        long ticket_id = ticketDAO.findNextAutoIncrement();
        for(TicketSeat seat : seats){
            seat.setTicketId(ticket_id);
            if(!ticketSeatDAO.create(seat)){
                entityTransaction.rollback();
                entityTransaction.endTransaction();
                return false;
            }
        }
        if(!userDAO.update(user)){
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
        }
        entityTransaction.commit();
        entityTransaction.endTransaction();
        return true;
    }
}
