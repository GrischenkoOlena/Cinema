package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperUserTest {
    MapperUser mapperUser = new MapperUser();
    User user;
    UserDTO userDTO;

    @BeforeEach
    public void setup(){
        user = new User(1, "makar@i.ua", "1234", "Makar", 150.0, UserRole.ADMIN);
        userDTO = new UserDTO.UserDTOBuilder(1)
                .login("makar@i.ua")
                .password("1234")
                .name("Makar")
                .balance(150.0)
                .userRole(UserRole.ADMIN)
                .build();
    }

    @Test
    public void getUserDTOTest(){
        assertEquals(userDTO, mapperUser.getDTO(user));
    }

    @Test
    public void getUserTest(){
        assertEquals(user, mapperUser.getUser(userDTO));
    }
}
