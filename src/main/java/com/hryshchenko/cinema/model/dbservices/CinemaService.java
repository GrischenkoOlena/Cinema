package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.model.connectionpool.DBManager;

public abstract class CinemaService {
    protected DBManager dbManager = DBManager.getInstance();
}
