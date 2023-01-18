package com.hryshchenko.cinema.util;

import static com.hryshchenko.cinema.constant.FieldName.*;

import java.util.HashMap;
import java.util.Map;

public class OrderMapUtil {
    private final static Map<String, String> orders = new HashMap<>();
    static {
        orders.put("movieAsc", VIEW_FILM_TITLE);
        orders.put("movieDesc", VIEW_FILM_TITLE + " DESC");
        orders.put("dataAsc", VIEW_FILM_DATE);
        orders.put("dataDesc", VIEW_FILM_DATE + " DESC");
        orders.put("availableAsc", VIEW_AMOUNT_FREE_SEATS);
        orders.put("availableDesc", VIEW_AMOUNT_FREE_SEATS + " DESC");
        orders.put("defaultScreening", VIEW_FILM_DATE + " DESC");

        orders.put("nameAsc", USER_NAME);
        orders.put("nameDesc", USER_NAME + " DESC");
        orders.put("balanceAsc", USER_BALANCE);
        orders.put("balanceDesc", USER_BALANCE + " DESC");
        orders.put("defaultCustomer", USER_ID);

        orders.put("titleAsc", FILM_TITLE);
        orders.put("titleDesc", FILM_TITLE + " DESC");
        orders.put("durationAsc", FILM_DURATION);
        orders.put("durationDesc", FILM_DURATION + " DESC");
        orders.put("defaultFilm", FILM_ID);

        orders.put("dataTicketAsc", SCREENING_ID);
        orders.put("dataTicketDesc", SCREENING_ID + " DESC");
        orders.put("defaultTicket", TICKET_ID + " DESC");
    }

    public String getOrderBD(String order) {
        return "ORDER BY " + orders.get(order);
    }
}
