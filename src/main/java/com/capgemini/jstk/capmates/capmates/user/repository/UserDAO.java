package com.capgemini.jstk.capmates.capmates.user.repository;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDAO {

    private Map<Integer,User> listOfUser;

    public Set<User> getUsers(){
        return listOfUser.values().stream().collect(Collectors.toSet());
    }

    public User getUser(int id){
        return listOfUser.get(id);
    }


    public void updateUser(User user){
        listOfUser.put(user.getId(), user);
    }




}
