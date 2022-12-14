package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping(value = "/player/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Integer id) throws ResourceNotFoundException {
        List<Player> result = playerService.getByID(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public ResponseEntity<?> postPlayer(@RequestBody Player newPlayer) throws ResourceException {
        playerService.checkPostPlayer(newPlayer);
        List<Player> result = playerService.postPlayer(newPlayer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/player/update/{player_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePlayer(@RequestBody Player newPlayer, @PathVariable Integer player_id) throws ResourceNotFoundException, ResourceException {
        playerService.checkUpdatePlayer(newPlayer);
        List<Player> result = playerService.updatePlayer(newPlayer, player_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/player/login/{player_id}")
    public ResponseEntity<?> loginPlayer(@PathVariable Integer player_id) throws ResourceNotFoundException {
        List<Player> result = playerService.loginPlayer(player_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/player/logout/{player_id}")
    public ResponseEntity<?> logoutPlayer(@PathVariable Integer player_id) throws ResourceNotFoundException {
        List<Player> result = playerService.logoutPlayer(player_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/players/delete/{player_id}")
    public void deletePlayer(@PathVariable Integer player_id)
            throws ResourceNotFoundException {

        playerService.deletePlayerById(player_id);
    }
}

