package com.hryshchenko.cinema.constant.enums;

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
