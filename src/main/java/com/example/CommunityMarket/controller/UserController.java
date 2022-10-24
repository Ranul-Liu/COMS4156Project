package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.User;
import com.example.CommunityMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByTemplate(
            @RequestParam(value = "user_id", required = false) Integer user_id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email) {

        // get results
        List<User> result = userService.getUserByTemplate(user_id, username, email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody User newUser) {
        userService.checkPostUser(newUser);
        List<User> result = userService.postUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        userService.checkPostUser(newUser);
        List<User> result = userService.updateUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
