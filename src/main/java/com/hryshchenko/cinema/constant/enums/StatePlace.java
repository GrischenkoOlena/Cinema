package com.hryshchenko.cinema.constant.enums;

/**
 * State place enum. Use to determine free or sold seats in a cinema.
 *
 * @author Olena Hryshchenko
 */

public enum StatePlace {
    FREE(1), SOLD(2);

    private final Integer id;

    StatePlace(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
