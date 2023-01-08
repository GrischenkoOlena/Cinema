package com.hryshchenko.cinema.constant.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum StateScreening {
    ACTIVE (1),
    CANCELED(2),
    PAST(3),
    PLANED(4);

    private final Integer id;
    private static final Map<Integer, StateScreening> ENUM_MAP;

    StateScreening(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    static {
        Map<Integer, StateScreening> map = new HashMap<>();
        for(StateScreening val : StateScreening.values()){
            map.put(val.getId(), val);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
    public static StateScreening getValueFromId(Integer id){
        return ENUM_MAP.get(id);
    }
}
