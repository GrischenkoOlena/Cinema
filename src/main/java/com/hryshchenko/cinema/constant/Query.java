package com.hryshchenko.cinema.constant;

/**
 * Interface represents all SQL query.
 *
 * @author  Olena Hryshchenko.
 */

public interface Query {
    String GET_ALL_USERS = "SELECT * FROM user orderField LIMIT ?, ?";
    String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    String CREATE_USER =
            "INSERT INTO user(login, password, user_name, balance, role_id) VALUES (?, ?, ?, ?, ?)";
    String DELETE_USER = "DELETE FROM user WHERE login = ?";
    String UPDATE_USER = "UPDATE user SET login = ?, user_name = ?, balance = ? WHERE user_id = ?";
    String COUNT_USER = "SELECT count(*) FROM user";
    String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    String GET_ALL_CATEGORIES = "SELECT * FROM category LIMIT ?, ?";
    String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id = ?";
    String CREATE_CATEGORY = "INSERT INTO category (category, price) VALUES (?, ?)";
    String DELETE_CATEGORY = "DELETE FROM category WHERE category_id = ?";
    String UPDATE_CATEGORY = "UPDATE category SET price = ? WHERE category_id = ?";

    String GET_ALL_FILMS = "SELECT * FROM film orderField LIMIT ?, ?";
    String GET_FILM_BY_ID = "SELECT * FROM film WHERE film_id = ?";
    String GET_FILM_BY_GENRE = "SELECT * FROM film WHERE genre_id = ? orderField LIMIT ?, ?";
    String CREATE_FILM =
            "INSERT INTO film (title, director, cast, description, genre_id, duration) VALUES (?, ?, ?, ?, ?, ?)";
    String DELETE_FILM = "DELETE FROM film WHERE film_id = ?";
    String UPDATE_FILM = "UPDATE film SET title = ?, director = ?, cast = ?, description = ?, duration = ? " +
            "WHERE film_id = ?";
    String COUNT_FILM = "SELECT count(*) FROM film";
    String COUNT_FILM_BY_GENRE = "SELECT count(*) FROM film WHERE genre_id = ?";

    String GET_ALL_GENRES = "SELECT * FROM genre LIMIT ?, ?";
    String GET_GENRE_BY_ID = "SELECT * FROM genre WHERE genre_id = ?";
    String CREATE_GENRE = "INSERT INTO genre (genre) VALUES (?)";
    String DELETE_GENRE = "DELETE FROM genre WHERE genre_id = ?";
    String UPDATE_GENRE = "UPDATE genre SET genre = ? WHERE genre_id = ?";

    String GET_ALL_SEATS = "SELECT * FROM seat";
    String GET_SEAT_BY_ID = "SELECT * FROM seat WHERE seat_id = ?";
    String UPDATE_SEAT = "UPDATE seat SET category_id = ? WHERE seat_id = ?";
    String GET_MAX_ROW = "SELECT max(line) FROM seat";
    String GET_MAX_PLACE = "SELECT max(place) FROM seat";

    String GET_ALL_SCREENINGS = "SELECT * FROM screening ORDER BY orderField LIMIT ?, ?";
    String GET_SCREENING_BY_ID = "SELECT * FROM screening WHERE screening_id = ?";
    String GET_SCREENINGS_BY_DATE = "SELECT * FROM screening WHERE film_date = ? ORDER BY time_begin";
    String CREATE_SCREENING = "INSERT INTO screening (film_id, film_date, time_begin, state_id) VALUES (?, ?, ?, ?)";
    String DELETE_SCREENING = "DELETE FROM screening WHERE screening_id = ?";
    String UPDATE_SCREENING = "UPDATE screening SET state_id = ? WHERE screening_id = ?";
    String COUNT_SCREENING = "SELECT count(*) FROM screening";
    String GET_SCREENINGS_BY_AVAILABLE = "SELECT * FROM screening WHERE film_date >= ? " +
            "AND state_id = (SELECT state_id FROM state WHERE state = 'active') orderField";
    String COUNT_SCREENING_BY_AVAILABLE = "SELECT count(*) FROM screening WHERE film_date >= ? " +
            "AND state_id = (SELECT state_id FROM state WHERE state = 'active')";

    String GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE ticket_id = ?";
    String GET_TICKETS_BY_USER = "SELECT * FROM ticket WHERE user_id = ? orderField LIMIT ?, ?";
    String CREATE_TICKET = "INSERT INTO ticket (screening_id, user_id, ticket_count) VALUES (?, ?, ?)";
    String DELETE_TICKET = "DELETE FROM ticket WHERE ticket_id = ?";
    String UPDATE_TICKET = "UPDATE ticket SET ticket_count = ? WHERE ticket_id = ?";
    String COUNT_TICKETS_BY_USER = "SELECT count(*) FROM ticket WHERE user_id = ?";
    String GET_TICKETS_BY_USER_DATE = "SELECT * FROM ticket WHERE user_id = ? " +
                "AND screening_id IN (SELECT screening_id FROM screening WHERE film_date = ?) orderField LIMIT ?, ?";
    String COUNT_TICKETS_BY_USER_DATE = "SELECT count(*) FROM ticket WHERE user_id = ? " +
                "AND screening_id IN (SELECT screening_id FROM screening WHERE film_date = ?)";

    String GET_NEXT_AUTOINCREMENT = "SELECT max(ticket_id) FROM ticket";

    String CREATE_TICKET_SEAT = "INSERT INTO ticket_seat (ticket_id, seat_id) VALUES (?, ?)";
    String DELETE_TICKET_SEAT = "DELETE FROM ticket_seat WHERE ticket_id = ?";

    String GET_SEATS_BY_TICKET = "SELECT * FROM seat " +
            "WHERE seat_id IN (SELECT seat_id from ticket_seat WHERE ticket_id = ?)";

    String GET_FREE_SEAT_BY_SCREENING = "SELECT * FROM seat WHERE seat_id NOT IN " +
            "(SELECT seat_id FROM ticket_seat WHERE ticket_id IN " +
                "(SELECT ticket_id FROM ticket WHERE screening_id = ?))";

    String GET_COUNT_FREE_SEAT_BY_SCREENING = "SELECT count(*) FROM seat WHERE seat_id NOT IN " +
            "(SELECT seat_id FROM ticket_seat WHERE ticket_id IN " +
                "(SELECT ticket_id FROM ticket WHERE screening_id = ?))";

    String COUNT_SCREENING_VIEW = "SELECT count(*) FROM screeningWithSeat";
    String GET_ALL_SCREENING_VIEW = "SELECT * FROM screeningWithSeat orderField LIMIT ?, ?";
    String COUNT_SCREENING_VIEWS_BY_AVAILABLE = "SELECT count(*) FROM screeningWithSeat " +
                                            "WHERE film_date >= ? AND state = 'active'";
    String GET_SCREENING_VIEWS_BY_AVAILABLE = "SELECT * FROM screeningWithSeat WHERE film_date >= ? " +
                "AND state = 'active' orderField LIMIT ?, ?";

    String GET_ATTENDANCE = "SELECT film_date, count(*), count(distinct title), sum(amount_free_places) " +
            "FROM screeningWithSeat GROUP BY film_date ORDER BY film_date DESC LIMIT ?, ?";

    String COUNT_DATE_ATTENDANCE = "SELECT count(distinct film_date) FROM screeningWithSeat";

}
