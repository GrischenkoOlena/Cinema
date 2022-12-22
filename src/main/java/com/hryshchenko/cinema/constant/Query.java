package com.hryshchenko.cinema.constant;

public interface Query {
    String GET_ALL_USERS = "SELECT * FROM user LIMIT ?, ?";
    String GET_USER_BY_ID = "SELECT * FROM user WHERE login = ?";
    String CREATE_USER =
            "INSERT INTO user(login, password, user_name, balance, role_id) VALUES (?, ?, ?, ?, ?)";
    String DELETE_USER = "DELETE FROM user WHERE login = ?";
    String UPDATE_USER = "UPDATE user SET balance = ? WHERE login = ?";

    String GET_ALL_CATEGORIES = "SELECT * FROM category LIMIT ?, ?";
    String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id = ?";
    String CREATE_CATEGORY = "INSERT INTO category (category, price) VALUES (?, ?)";
    String DELETE_CATEGORY = "DELETE FROM category WHERE category_id = ?";
    String UPDATE_CATEGORY = "UPDATE category SET price = ? WHERE category_id = ?";

    String GET_ALL_FILMS = "SELECT * FROM film LIMIT ?, ?";
    String GET_FILM_BY_TITLE = "SELECT * FROM film WHERE title = ?";
    String CREATE_FILM =
            "INSERT INTO film (title, director, cast, description, genre_id, duration) VALUES (?, ?, ?, ?, ?, ?)";
    String DELETE_FILM = "DELETE FROM film WHERE film_id = ?";

    String GET_ALL_GENRES = "SELECT * FROM genre LIMIT ?, ?";
    String GET_GENRE_BY_ID = "SELECT * FROM genre WHERE genre_id = ?";
    String CREATE_GENRE = "INSERT INTO genre (genre) VALUES (?)";
    String DELETE_GENRE = "DELETE FROM genre WHERE genre_id = ?";
    String UPDATE_GENRE = "UPDATE genre SET genre = ? WHERE genre_id = ?";

    String GET_ALL_SEATS = "SELECT * FROM seat";
    String GET_SEAT_BY_ID = "SELECT * FROM seat WHERE seat_id = ?";
    String UPDATE_SEAT = "UPDATE seat SET category_id = ? WHERE seat_id = ?";


}
