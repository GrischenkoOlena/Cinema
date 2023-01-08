package com.hryshchenko.cinema.constant.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum UserRole {
    ADMIN (1),
    CLIENT (2);
    private final Integer id;
    private static final Map<Integer, UserRole> ENUM_MAP;

    UserRole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    static {
        Map<Integer, UserRole> map = new HashMap<>();
        for(UserRole val : UserRole.values()){
            map.put(val.getId(), val);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
    public static UserRole getValueFromId(Integer id){
        return ENUM_MAP.get(id);
    }
}
