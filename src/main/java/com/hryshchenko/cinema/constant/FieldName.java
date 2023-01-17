package com.hryshchenko.cinema.constant;

public interface FieldName {
    String USER_ID = "user_id";
    String USER_LOGIN = "login";
    String USER_PASSWORD = "password";
    String USER_NAME = "user_name";
    String USER_BALANCE = "balance";
    String USER_ROLE_ID = "role_id";

    String FILM_ID = "film_id";
    String FILM_TITLE = "title";
    String FILM_DIRECTOR = "director";
    String FILM_CAST = "cast";
    String FILM_DESCRIPTION = "description";
    String FILM_GENRE_ID = "genre_id";
    String FILM_DURATION = "duration";

    String CATEGORY_ID = "category_id";
    String CATEGORY_TITLE = "category";
    String CATEGORY_PRICE = "price";

    String GENRE_ID = "genre_id";
    String GENRE_TITLE = "genre";

    String SCREENING_ID = "screening_id";
    String SCREENING_FILM_ID = "film_id";
    String SCREENING_FILM_DATE = "film_date";
    String SCREENING_TIME_BEGIN = "time_begin";
    String SCREENING_STATE_ID = "state_id";

    String SEAT_ID = "seat_id";
    String SEAT_LINE = "line";
    String SEAT_PLACE = "place";
    String SEAT_CATEGORY_ID = "category_id";

    String TICKET_ID = "ticket_id";
    String TICKET_SCREENING_ID = "screening_id";
    String TICKET_USER_ID = "user_id";
    String TICKET_COUNT = "ticket_count";

    String TICKET_SEAT_ID = "id";
    String TICKET_SEAT_TICKET_ID = "ticket_id";
    String TICKET_SEAT_SEAT_ID = "seat_id";

    String VIEW_SCREENING_ID = "screening_id";
    String VIEW_FILM_TITLE = "title";
    String VIEW_FILM_DATE = "film_date";
    String VIEW_TIME_BEGIN = "time_begin";
    String VIEW_STATE = "state";
    String VIEW_AMOUNT_FREE_SEATS = "amount_free_places";

}
