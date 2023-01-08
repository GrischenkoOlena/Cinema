package com.hryshchenko.cinema.model.dbservices;

import com.hryshchenko.cinema.model.connectionpool.DBManager;

public interface ICinemaService {
    DBManager dbManager = DBManager.getInstance();
}
