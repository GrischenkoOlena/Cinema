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

    private TicketDAO ticketDAO = new TicketDAO();
    private UserDAO userDAO = new UserDAO();
    private TicketSeatDAO ticketSeatDAO = new TicketSeatDAO();
    private EntityTransaction entityTransaction = new EntityTransaction();

    private MapperTicket mapperTicket = new MapperTicket();
    private MapperUser mapperUser = new MapperUser();
    private MapperSeat mapperSeat = new MapperSeat();

    private SeatService seatService = AppContext.getInstance().getSeatService();

    public boolean buyTicket(TicketDTO ticketDTO) throws NotEnoughMoneyException, SeatHasSoldException {
        double cost = getCost(ticketDTO);

        Ticket ticket = mapperTicket.getTicket(ticketDTO);
        User user = mapperUser.getUser(ticketDTO.getUser());
        if(user.getBalance() > cost){
            user.setBalance(user.getBalance() - cost);
        } else {
            throw new NotEnoughMoneyException("you don't have enough money to buy ticket");
        }

        List<TicketSeat> ticketHasSeats = getTicketHasSeats(ticketDTO);
        List<Seat> seats = mapperSeat.getListSeat(ticketDTO.getSeats());

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

    public boolean turnTicket(TicketDTO ticketDTO){
        Ticket ticket = mapperTicket.getTicket(ticketDTO);

        User user = mapperUser.getUser(ticketDTO.getUser());
        double cost = getCost(ticketDTO);
        user.setBalance(user.getBalance() + cost);

        boolean result;
        try {
            result = deleteTicket(ticket, user);
        } catch (DAOException e) {
            log.error(e.getMessage());
            return false;
        }
        return result;
    }

    private List<TicketSeat> getTicketHasSeats(TicketDTO ticketDTO) {
        List<TicketSeat> ticketHasSeats = new ArrayList<>();
        for(SeatDTO seatDTO : ticketDTO.getSeats()){
            TicketSeat ticketSeat = new TicketSeat();
            ticketSeat.setSeatId((int) seatDTO.getId());
            ticketHasSeats.add(ticketSeat);
        }
        return ticketHasSeats;
    }

    private double getCost(TicketDTO ticketDTO) {
        return ticketDTO.getSeats().stream().mapToDouble(temp -> temp.getCategory().getPrice()).sum();
    }

    private boolean createTicket(Ticket ticket, List<TicketSeat> seats, User user) throws DAOException{
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

    private boolean deleteTicket(Ticket ticket, User user) throws DAOException {
        entityTransaction.initTransaction(ticketDAO, userDAO, ticketSeatDAO);

        TicketSeat ticketSeat = new TicketSeat();
        ticketSeat.setTicketId(ticket.getId());
        if (!ticketSeatDAO.delete(ticketSeat)) {
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
        }
        if (!ticketDAO.delete(ticket)){
            entityTransaction.rollback();
            entityTransaction.endTransaction();
            return false;
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
