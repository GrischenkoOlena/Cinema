package com.hryshchenko.cinema.model.service;

import com.hryshchenko.cinema.model.connectionpool.DBManager;

public interface ICinemaService {
    DBManager dbManager = DBManager.getInstance();
}
