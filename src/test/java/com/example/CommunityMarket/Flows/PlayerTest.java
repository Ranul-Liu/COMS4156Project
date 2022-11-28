package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.PlayerRepository;
import com.example.CommunityMarket.service.PlayerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerTest {

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepo;

    // test postPlayer method
    @Test
    public void testPostPlayer() {

        // Initialize a Player object to save into the database
        // player_id = null because it will be auto-generated
        Player newPlayerToPost = new Player(null,
                "testPostPlayer@gmail.com",
                "testPostPlayer",
                true);

        // Mock saving the Player object
        Mockito.when(playerRepo.save(newPlayerToPost)).thenReturn(new Player(1,
                "testPostPlayer@gmail.com",
                "testPostPlayer",
                true));

        //assert that the return value of Post are as expected
        Player resultOfPostPlayer = playerService.postPlayer(newPlayerToPost).get(0);
        assertEquals(resultOfPostPlayer.getPlayername(), "testPostPlayer");
        assertEquals(resultOfPostPlayer.getEmail(), "testPostPlayer@gmail.com");
        assertEquals(resultOfPostPlayer.getLogin(), true);
        // did not assert player_id because it is auto-generated
    }


    // test updatePlayer method
    @Test
    public void testUpdatePlayer() throws ResourceNotFoundException {

        // Initialize updated player
        Player newPlayerToUpdate = new Player(1,
                "testUpdatePlayer@gmail.com",
                "testUpdatePlayer",
                true);

        // expected result after update
        Player expectedResult = new Player(1,
                "emanueld@gmail.com",
                "123abc",
                false);

        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(String.valueOf(newPlayerToUpdate.getPlayerID()))).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(expectedResult)).thenReturn(expectedResult);

        Player resultOfUpdatePlayer = playerService.updatePlayer(expectedResult).get(0);
        // assert the fields
        assertEquals(resultOfUpdatePlayer.getPlayerID(), expectedResult.getPlayerID());
        assertEquals(resultOfUpdatePlayer.getEmail(), expectedResult.getEmail());
        assertEquals(resultOfUpdatePlayer.getPlayername(), expectedResult.getPlayername());
        assertEquals(resultOfUpdatePlayer.getLogin(), expectedResult.getLogin());
    }

    //Make sure exception raised when the player does not exist
    @Test(expected = ResourceNotFoundException.class)
    public void testExceptionUpdatePlayer() throws ResourceNotFoundException {

        // Initialize updated player
        Player updatedPlayer = new Player(22,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        // playerID does not exist
        Mockito.when(playerRepo.existsById(updatedPlayer.getPlayerID().toString())).thenReturn(false);

        //call updatePlayer method
        playerService.updatePlayer(updatedPlayer);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testExceptionDeleteUserById() throws ResourceNotFoundException {

        Player player = new Player(22,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        // userID does not exist
        Mockito.when(playerRepo.existsById("22")).thenReturn(false);

        //call deleteUserById method
        playerService.deletePlayerById(player);
    }

    @Test
    public void testEmailIsValid() {
        //  string containing only whitespaces
        String test = "testEmailIsValid@gmail.com";

        // assert email is not empty and is within the length limit
        assertTrue(playerService.checkEmailLength(test));
        // assert email is in the right form
        assertTrue(playerService.isValidEmail(test));
    }

    @Test
    public void testLongEmailIsInvalid() {

        // Initialize a string containing > 128 chars
        String test = "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz";

        // assert email is not empty and is within the length limit
        assertFalse(playerService.checkEmailLength(test));
        // assert email is in the right form
        assertFalse(playerService.isValidEmail(test));
    }

    @Test
    public void testEmptyEmailIsInvalid() {
        //  string containing only whitespaces
        String test = "   ";

        // assert email is not empty and is within the length limit
        assertFalse(playerService.checkEmailLength(test));
        // assert email is in the right form
        assertFalse(playerService.isValidEmail(test));
    }

    @Test
    public void testPlayerNameIsValid() {
        String test = "playernamevalid";

        assertTrue(playerService.checkPlayernameLength(test));
    }

    @Test
    public void testLongPlayerNameIsInvalid() {

        // Initialize a string containing > 128 chars
        String test = "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz";

        assertFalse(playerService.checkPlayernameLength(test));
    }

    @Test
    public void testEmptyPlayerNameIsInvalid() {
        //  string containing only whitespaces
        String test = "   ";

        assertFalse(playerService.checkPlayernameLength(test));

    }


    // Test that exception is raised when email is invalid
    @Test(expected = ResourceException.class)
    public void testCheckInputsEmail1() throws ResourceException {

        // Initialize test player with invalid email
        Player testPlayer = new Player(432,
                "william23gmail.com",
                "william23234",
                true);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when email is null
    @Test(expected = ResourceException.class)
    public void testCheckInputsEmail2() throws ResourceException {

        // Initialize test player with invalid email
        Player testPlayer = new Player(54,
                "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld@gmail.com",
                "emanueld12345",
                true);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when playername is invalid
    @Test(expected = ResourceException.class)
    public void testCheckInputsPlayername1() throws ResourceException {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(45,
                "william123@gmail.com",
                "1111111111111111111111111111111111111111",
                true);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when playername is invalid
    @Test(expected = ResourceException.class)
    public void testCheckInputsPlayername2() throws ResourceException {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(45,
                "william123@gmail.com",
                "   ",
                true);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when playername is null
    @Test(expected = ResourceException.class)
    public void testCheckInputsPlayername3() throws ResourceException {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(null,
                "william123@gmail.com",
                null,
                true);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when player_id provided that is not null
    @Test(expected = ResourceException.class)
    public void testCheckPostInputs() throws ResourceException {

        // Initialize test user with player_id that is not null
        Player testPlayer = new Player(22,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        playerService.checkPostPlayer(testPlayer);
    }

    @Test
    public void testCheckPostInputs2() throws ResourceException {

        Player testPlayer = new Player(null,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        playerService.checkPostPlayer(testPlayer);
    }

    // Test that exception is raised when player_id not provided
    @Test (expected = ResourceException.class)
    public void testCheckPutInputs2() throws ResourceException {

        // Initialize test user with player_id that is null
        Player testPlayer = new Player(null,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        playerService.checkUpdatePlayer(testPlayer);
    }


    @Test
    public void testCheckPutInputs() throws ResourceException {

        Player testPlayer = new Player(23,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        playerService.checkUpdatePlayer(testPlayer);
    }


    @Test
    public void testCheckGetByIdExists() throws ResourceNotFoundException {

        Player newPlayer = new Player(77,
                "william23gmail.com",
                "william23234",
                true);
        Integer player_id = newPlayer.getPlayerID();

        Optional<Player> optPlayer = Optional.of(newPlayer);
        Mockito.when(playerRepo.findById(player_id.toString())).thenReturn(optPlayer);
        assertEquals(List.of(optPlayer.get()), playerService.getByID(player_id));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testCheckGetByIdNotExists() throws ResourceNotFoundException {
        Integer player_id = 1;
        Optional<Player> optPlayer = Optional.empty();
        Mockito.when(playerRepo.findById(player_id.toString())).thenReturn(optPlayer);
        playerService.getByID(player_id);

    }

    @Test
    public void testGetPlayersByTemplate() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Player newPlayer = new Player(984,
                "william123@gmail.com",
                "william123",
                true);

        Mockito.when(playerRepo.findByTemplate(null,
                "william123@gmail.com", "william123",true)).thenReturn(List.of(newPlayer));
        Player result = playerService.getPlayerByTemplate(null,
                "william123@gmail.com", "william123",true).get(0);
        assertEquals(result.getPlayerID(), result.getPlayerID());

    }

    @Test
    public void testLoginPlayer() throws ResourceNotFoundException {

        // Initialize login player
        Player newPlayerToLogin = new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                false);

        // expected result after login
        Player expectedResult = new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                true);

        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(String.valueOf(newPlayerToLogin.getPlayerID()))).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(expectedResult)).thenReturn(expectedResult);

        Player resultOfUpdatePlayer = playerService.loginPlayer(expectedResult).get(0);
        // assert the fields
        assertEquals(resultOfUpdatePlayer.getLogin(), expectedResult.getLogin());
    }

    //Make sure exception raised when the player does not exist
    @Test(expected = ResourceNotFoundException.class)
    public void testExceptionLoginPlayer() throws ResourceNotFoundException {

        // Initialize login player
        Player loginPlayer =  new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                false);

        // playerID does not exist
        Mockito.when(playerRepo.existsById(loginPlayer.getPlayerID().toString())).thenReturn(false);

        //call loginPlayer method
        playerService.loginPlayer(loginPlayer);
    }

    @Test
    public void testLogoutPlayer() throws ResourceNotFoundException {

        // Initialize logout player
        Player newPlayerToLogout = new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                true);

        // expected result after logout
        Player expectedResult = new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                false);

        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(String.valueOf(newPlayerToLogout.getPlayerID()))).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(expectedResult)).thenReturn(expectedResult);

        Player resultOfUpdatePlayer = playerService.logoutPlayer(expectedResult).get(0);
        // assert the fields
        assertEquals(resultOfUpdatePlayer.getLogin(), expectedResult.getLogin());
    }

    //Make sure exception raised when the player does not exist
    @Test(expected = ResourceNotFoundException.class)
    public void testExceptionLogoutPlayer() throws ResourceNotFoundException {

        // Initialize logout player
        Player logoutPlayer = new Player(1,
                "testLoginPlayer@gmail.com",
                "testLoginPlayer",
                true);

        // playerID does not exist
        Mockito.when(playerRepo.existsById(logoutPlayer.getPlayerID().toString())).thenReturn(false);

        //call logoutPlayer method
        playerService.logoutPlayer(logoutPlayer);
    }


}
