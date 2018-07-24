package com.capgemini.jstk.capmates.capmates.mapper;

public interface MapperObject<T,E> {

    E mapFromDAO(T object);
    T mapfromDTO(E object);
}
