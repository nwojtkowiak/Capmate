package com.capgemini.jstk.capmates.capmates.mapper;

import com.capgemini.jstk.capmates.capmates.user.repository.User;
import com.capgemini.jstk.capmates.capmates.user.service.UserDTO;

public class MapperUser implements MapperObject<User, UserDTO> {

    @Override
    public UserDTO mapFromDAO(User object) {

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(object.getFirstName());
        userDTO.setLastName(object.getLastName());
        userDTO.setPassword(object.getPassword());
        userDTO.setEmail(object.getEmail());
        userDTO.setMotto(object.getMotto());
        userDTO.setId(object.getId());

        return userDTO;
    }

    @Override
    public User mapfromDTO(UserDTO object) {

        User user = new User();

        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setPassword(object.getPassword());
        user.setEmail(object.getEmail());
        user.setMotto(object.getMotto());
        user.setId(object.getId());

        return user;
    }
}
