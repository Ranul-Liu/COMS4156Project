package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public ResponseEntity<?> getPlayerByTemplate(
            @RequestParam(value = "player_id", required = false) Integer player_id,
            @RequestParam(value = "playername", required = false) String playername,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "login", required = false) Boolean login) {

        // get results
        List<Player> result = playerService.getPlayerByTemplate(player_id, playername, email, login);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public ResponseEntity<?> postPlayer(@RequestBody Player newPlayer) {
        playerService.checkPostPlayer(newPlayer);
        List<Player> result = playerService.postPlayer(newPlayer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/player", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePlayer(@RequestBody Player newPlayer) {
        playerService.checkUpdatePlayer(newPlayer);
        List<Player> result = playerService.updatePlayer(newPlayer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/players/login")
    public ResponseEntity<?> loginPlayer(@RequestBody Player player) {
        List<Player> result = playerService.loginPlayer(player);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/players/logout")
    public ResponseEntity<?> logoutPlayer(@RequestBody Player player) {
        List<Player> result = playerService.logoutPlayer(player);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/players/delete")
    public void deletePlayer(@RequestBody Player player) {
        playerService.deletePlayerById(player);
    }
}

