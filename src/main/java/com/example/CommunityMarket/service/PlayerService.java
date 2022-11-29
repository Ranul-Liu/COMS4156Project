package com.example.CommunityMarket.service;

import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.repository.PlayerRepository;
import com.example.CommunityMarket.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepo;

    private final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";


    //get by ID
    public List<Player> getByID(Integer player_id) throws ResourceNotFoundException {
        Optional<Player> result = playerRepo.findById(player_id);
        if (result.isPresent()) {
            Player playerResult = result.get();
            return List.of(playerResult);
        } else {
            throw new ResourceNotFoundException("Player not found by ID in DB.");
        }

    }

    //get operation
    public List<Player> getPlayerByTemplate(Integer player_id,
                                        String playername,
                                        String email, Boolean login){
        return playerRepo.findByTemplate(player_id,playername, email, login);
    }

    //post operation
    public List<Player> postPlayer(Player player) {
        Player result = playerRepo.save(player);
        return List.of(result);
    }

    //put operation
    public List<Player> updatePlayer(Player player) throws ResourceNotFoundException {
        if (getByID(player.getPlayerID()).size() >= 1) {
            Player result = playerRepo.save(player);
            return List.of(result);
        } else {
            throw new ResourceNotFoundException("Player not found by ID in DB, cannot update");
        }
    }

    public List<Player> loginPlayer(Integer player_id) throws ResourceNotFoundException {
        Optional<Player> result = playerRepo.findById(player_id);
        if (result.isPresent()) {
            Player playerResult = result.get();
            playerResult.setLogin(true);
            playerRepo.save(playerResult);
            return List.of(playerResult);
        } else {
            throw new ResourceNotFoundException("Player not found by ID in DB, cannot login");
        }
    }

    public List<Player> logoutPlayer(Integer player_id) throws ResourceNotFoundException {
        Optional<Player> result = playerRepo.findById(player_id);
        if (result.isPresent()) {
            Player playerResult = result.get();
            playerResult.setLogin(false);
            playerRepo.save(playerResult);
            return List.of(playerResult);
        } else {
            throw new ResourceNotFoundException("Player not found by ID in DB, cannot logout");
        }
    }

    // delete operation
    public void deletePlayerById(Player player) throws ResourceNotFoundException{
        if (getByID(player.getPlayerID()).size() >= 1) {
            playerRepo.deleteById(player.getPlayerID());
        } else {
            throw new ResourceNotFoundException("Player not found in DB, cannot delete");
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

    public boolean checkPlayernameLength(String playername) {
        return !playername.isBlank() && playername.length() <= 32;
    }

    public boolean checkEmailLength(String email) {
        return !email.isBlank() && email.length() <= 128;
    }

    // check and sanitize inputs
    public void checkInputs(Player player) throws ResourceException {
        try {
            if (!checkPlayernameLength(player.getPlayername())) {
                throw new ResourceException("Playername must be between 1-32 characters");
            }
            else if (!checkEmailLength(player.getEmail())) {
                throw new ResourceException("Email must be between 1-128 characters");
            }
            else if (!isValidEmail(player.getEmail())) {
                throw new ResourceException("Invalid email");
            }
        } catch (NullPointerException e) {
            throw new ResourceException("Player formatted incorrectly please provide the following:\n" +
                    "playername, email");
        }
    }

    public void checkPostPlayer(Player player) throws ResourceException {
        if (player.getPlayerID() != null) {
            throw new ResourceException("Do not provide player_id");
        }
        checkInputs(player);
    }

    public void checkUpdatePlayer(Player player) throws ResourceException {
        if (player.getPlayerID() == null) {
            throw new ResourceException("Please provide player_id");
        }
        checkInputs(player);
    }

    public void checkPlayerLoggedInById(Integer player_id) throws ResourceException, ResourceNotFoundException {
        try {
            List<Player> player = getByID(player_id);
            if (!(player.size() >= 1)) {
                throw new ResourceNotFoundException("player_id not found");
            }
            if (player.get(0).getLogin() == false) {
                throw new ResourceException("player_id not logged in");
            }
        }
        catch(Exception e) {
            throw e;
        }
    }
}
