package com.hryshchenko.cinema.model.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum State {
    ACTIVE (1),
    CANCELED(2),
    PAST(3),
    PLANED(4);

    private final Integer id;
    private static final Map<Integer, State> ENUM_MAP;

    State(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    static {
        Map<Integer, State> map = new HashMap<>();
        for(State val : State.values()){
            map.put(val.getId(), val);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
    public static State getValueFromId(Integer id){
        return ENUM_MAP.get(id);
    }
}
