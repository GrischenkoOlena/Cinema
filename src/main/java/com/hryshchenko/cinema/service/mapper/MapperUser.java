package com.hryshchenko.cinema.service.mapper;

import com.hryshchenko.cinema.dto.UserDTO;
import com.hryshchenko.cinema.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MapperUser implements IMapperService<User, UserDTO> {
    @Override
    public UserDTO getDTO(User entity) {
        return new UserDTO.UserDTOBuilder(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .name(entity.getName())
                .balance(entity.getBalance())
                .userRole(entity.getRole())
                .build();
    }

    @Override
    public List<UserDTO> getListDTO(List<User> entities) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : entities){
            userDTOList.add(getDTO(user));
        }
        return userDTOList;
    }
}
