package com.example.CommunityMarket.service;

import com.example.CommunityMarket.Repository.UserRepository;
import com.example.CommunityMarket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    private final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";


    //get by ID
    public List<User> getByID(Integer user_id) {
        Optional<User> result = userRepo.findById(user_id.toString());
        if (result.isPresent()) {
            User userResult = result.get();
            return List.of(userResult);
        } else {
            throw new IllegalArgumentException("User not found by ID in DB.");
        }

    }

    //get operation
    public List<User> getUserByTemplate(Integer user_id,
                                        String username,
                                        String email, Integer login){
        return userRepo.findByTemplate(user_id,username, email, login);
    }

    //post operation
    public List<User> postUser(User user) {
        User result = userRepo.save(user);
        return List.of(result);
    }

    //put operation
    public List<User> updateUser(User user) throws IllegalArgumentException {
        if (getByID(user.getUserID()).size() >= 1) {
            User result = userRepo.save(user);
            return List.of(result);
        } else {
            throw new IllegalArgumentException("User not found by ID in DB, cannot update");
        }
    }

    // delete operation
    public void deleteUserById(User user) {
        if (getByID(user.getUserID()).size() >= 1) {
            userRepo.deleteById(user.getUserID().toString());
        } else {
            throw new IllegalArgumentException("User not found in DB, cannot delete");
        }
    }

    //check if an email is valid
    public boolean isValidEmail(String email) {
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // checking:    - length of string is <128 characters
    //              - that string is not blank ex "   "
    //              - string is initialize (not == null)
    // returns true if follows rules above, false otherwise
    public boolean checkIfInvalid(String string) {
        return string.length() > 128 || string.isBlank();
    }

    public boolean checkUsernameLength(String username) {
        return !username.isBlank() && username.length() <= 32;
    }

    public boolean checkEmailLength(String email) {
        return !email.isBlank() && email.length() <= 128;
    }

    // check and sanitize inputs
    public void checkInputs(User user) throws IllegalArgumentException {
        try {
            if (!checkUsernameLength(user.getUsername())) {
                throw new IllegalArgumentException("Username must be between 1-32 characters");
            }
            else if (!checkEmailLength(user.getEmail())) {
                throw new IllegalArgumentException("Email must be between 1-128 characters");
            }
            else if (!isValidEmail(user.getEmail())) {
                throw new IllegalArgumentException("Invalid email");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("User formatted incorrectly please provide the following:\n" +
                    "username, email");
        }
    }

    public void checkPostUser(User user) throws IllegalArgumentException {
        if (user.getUserID() != null) {
            throw new IllegalArgumentException("Do not provide user_id");
        }
        checkInputs(user);
    }

    public void checkUpdateUser(User user) throws IllegalArgumentException {
        if (user.getUserID() == null) {
            throw new IllegalArgumentException("Please provide user_id");
        }
        checkInputs(user);
    }
}
