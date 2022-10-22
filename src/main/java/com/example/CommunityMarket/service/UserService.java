package com.example.CommunityMarket.service;


import com.example.CommunityMarket.Repository.UserRepository;
import com.example.CommunityMarket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    //get by ID
    public List<User> getByID(String userID) {
        Optional<User> result = userRepo.findById(userID);
        if (result.isPresent()) {
            User userResult = result.get();
            return List.of(userResult);
        }
        return Collections.emptyList();
    }

    //get operation
    public List<User> getUserByTemplate(String userID,
                                        String username){
        return userRepo.findByTemplate(userID,username);
    }

    //post operation
    public List<User> postUser(User item) {
        User result = userRepo.save(item);
        return List.of(result);
    }

    //put operation
    public List<User> updateUser(User user) throws IllegalArgumentException {
        if (getByID(user.getUserID().toString()).size() >= 1) {
            User result = userRepo.save(user);
            return List.of(result);
        } else {
            throw new IllegalArgumentException("Resource not found by ID in DB, cannot update");
        }
    }

    // delete operation
    public void deleteUserById(User user) {
        if (getByID(user.getUserID().toString()).size() >= 1) {
            userRepo.deleteById(user.getUserID().toString());
        } else {
            throw new IllegalArgumentException("Resource not found in DB, cannot delete");
        }
    }

}
