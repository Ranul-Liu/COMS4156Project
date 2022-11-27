package com.example.CommunityMarket.Flows;


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
    public void testPostPlayer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize a Player object to save into the database
        // player_id = null because it will be auto-generated
        Player newPlayerToPost = new Player(null,
                "testPostPlayer@gmail.com",
                "testPostPlayer",
                1);

        // Mock saving the Player object
        Mockito.when(playerRepo.save(newPlayerToPost)).thenReturn(new Player(1,
                "testPostPlayer@gmail.com",
                "testPostPlayer",
                1));

        //assert that the return value of Post are as expected
        Player resultOfPostPlayer = playerService.postPlayer(newPlayerToPost).get(0);
        assertEquals(resultOfPostPlayer.getPlayername(), "testPostPlayer");
        assertEquals(resultOfPostPlayer.getEmail(), "testPostPlayer@gmail.com");
        assertEquals(resultOfPostPlayer.getLogin(), 1);
        // did not assert player_id because it is auto-generated
    }


    // test updatePlayer method
    @Test
    public void testUpdatePlayer() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated player
        Player newPlayerToUpdate = new Player(1,
                "testUpdatePlayer@gmail.com",
                "testUpdatePlayer",
                1);

        // expected result after update
        Player expectedResult = new Player(1,
                "emanueld@gmail.com",
                "123abc",
                0);

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
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdatePlayer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated player
        Player updatedPlayer = new Player(22,
                "Alice22@gmail.com",
                "Alice2233",
                1);

        // playerID does not exist
        Mockito.when(playerRepo.existsById(updatedPlayer.getPlayerID().toString())).thenReturn(false);

        //call updatePlayer method
        playerService.updatePlayer(updatedPlayer);
    }

    // Make sure that delete raises exception when player not found
    /*@Test(expected = IllegalArgumentException.class)
    public void testExceptionDeletePlayerById() throws {

    }*/

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

    // Test that exception is raised when email is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsEmail1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test player with invalid email
        Player testPlayer = new Player(432,
                "william23gmail.com",
                "william23234",
                1);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when email is null
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsEmail2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test player with invalid email
        Player testPlayer = new Player(54,
                "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld@gmail.com",
                "emanueld12345",
                1);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when playername is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsPlayername1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(45,
                "william123@gmail.com",
                "1111111111111111111111111111111111111111",
                1);

        playerService.checkInputs(testPlayer);
    }

    // Test that exception is raised when playername is null
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsPlayername2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(null,
                "william123@gmail.com",
                null,
                1);

        playerService.checkInputs(testPlayer);
    }

    @Test
    public void testCheckGetByIdExists() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Player newPlayer = new Player(77,
                "william23gmail.com",
                "william23234",
                1);
        Integer player_id = newPlayer.getPlayerID();

        Optional<Player> optPlayer = Optional.of(newPlayer);
        Mockito.when(playerRepo.findById(player_id.toString())).thenReturn(optPlayer);
        assertEquals(List.of(optPlayer.get()), playerService.getByID(player_id));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckGetByIdNotExists() {
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
                1);

        Mockito.when(playerRepo.findByTemplate(null,
                "william123@gmail.com", "william123",1)).thenReturn(List.of(newPlayer));
        Player result = playerService.getPlayerByTemplate(null,
                "william123@gmail.com", "william123",1).get(0);
        assertEquals(result.getPlayerID(), result.getPlayerID());

    }





}
