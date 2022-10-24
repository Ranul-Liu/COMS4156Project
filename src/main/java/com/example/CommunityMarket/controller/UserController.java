/*
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
            @RequestParam(value = "userID", required = false) String userID,
            @RequestParam(value = "username", required = false) String username) {


        // verify userID and logged in?

        // get results
        List<User> result = userService.getUserByTemplate(userID, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody User newUser) {
        List<User> result = userService.postUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        List<User> result = userService.updateUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
*/
