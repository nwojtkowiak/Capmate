package com.capgemini.jstk.capmates.capmates.mapper;

import com.capgemini.jstk.capmates.capmates.user.repository.User;
import com.capgemini.jstk.capmates.capmates.user.service.UserDTO;

public interface MapperObject<T,E> {

    E mapFromDAO(T object);
    T mapfromDTO(E object);
}
