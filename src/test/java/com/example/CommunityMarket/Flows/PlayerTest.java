package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.PlayerRepository;
import com.example.CommunityMarket.service.PlayerService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerTest {

    @Mock
    private PlayerRepository playerRepo;
    @InjectMocks
    private PlayerService playerService;


    // test method getByID
    // return player if the id exist
    @Test
    public void testCheckGetByIdExists() throws ResourceNotFoundException {

        Player newPlayer = new Player(77,
                "william23gmail.com",
                "william23234",
                true);
        Integer player_id = 77;

        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.of(newPlayer));
        assertEquals(playerService.getByID(player_id),List.of(newPlayer));

    }
    // test method getByID
    // throw exception when player_id does not exist
    @Test
    public void testCheckGetByIdNotExists() throws ResourceNotFoundException {
        Integer player_id = 1;
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->playerService.getByID(player_id));
    }
    // test GetPlayerByTemplate
    @Test
    public void testGetPlayersByTemplate() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Player newPlayer = new Player(984,
                "william123@gmail.com",
                "william123",
                true);

        Mockito.when(playerRepo.findByTemplate(null,
                "william123@gmail.com", "william123",true)).thenReturn(List.of(newPlayer));

        assertEquals(playerService.getPlayerByTemplate(null,
                "william123@gmail.com", "william123",true), List.of(newPlayer));

    }

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
        Mockito.when(playerRepo.save(newPlayerToPost)).thenReturn(newPlayerToPost);

        //assert that the return value of Post are as expected
        Player resultOfPostPlayer = playerService.postPlayer(newPlayerToPost).get(0);
        assertEquals(resultOfPostPlayer.getPlayername(), "testPostPlayer");
        assertEquals(resultOfPostPlayer.getEmail(), "testPostPlayer@gmail.com");
        assertEquals(resultOfPostPlayer.getLogin(), true);
        // did not assert player_id because it is auto-generated
    }


    // test updatePlayer method
    // method to update playername, email
    // cannot update player_id, login
    @Test
    public void testUpdatePlayer() throws ResourceNotFoundException {
        // Initialize player_id that needs update
        Integer update_player_id = 1;
        // Initialize updated player
        Player player_before_update = new Player(1,
                "before@gmail.com",
                "beforeUpdatePlayer",
                false);
        // player for update
        Player player_for_update = new Player(1,
                "after@gmail.com",
                "afterUpdatePlayer",
                false);

        // expected result after update
        Player player_after_update = new Player(1,
                "after@gmail.com",
                "afterUpdatePlayer",
                false);

        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(update_player_id)).thenReturn(Optional.of(player_before_update));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(player_for_update)).thenReturn(player_for_update);
        Player resultOfUpdatePlayer = playerService.updatePlayer(player_for_update,update_player_id).get(0);
        // assert the fields
        assertEquals(resultOfUpdatePlayer.getPlayerID(), player_after_update.getPlayerID());
        assertEquals(resultOfUpdatePlayer.getEmail(), player_after_update.getEmail());
        assertEquals(resultOfUpdatePlayer.getPlayername(), player_after_update.getPlayername());
        assertEquals(resultOfUpdatePlayer.getLogin(), player_after_update.getLogin());
    }

    //Throw Exception when player_id does not exist
    @Test
    public void testExceptionUpdatePlayer() throws ResourceNotFoundException {

        Integer update_player_id = 111;
        // player for update
        Player player_for_update = new Player(111,
                "after@gmail.com",
                "afterUpdatePlayer",
                false);

            //make the repo return an empty optional when the id does not exist
            Mockito.when(playerRepo.findById(update_player_id)).thenReturn(Optional.empty());
            assertThrows(ResourceNotFoundException.class,
                    ()->playerService.updatePlayer(player_for_update,update_player_id));
    }

    // Test Player Login method
    // update the login status to true when player_id exist
    @Test
    public void testLoginPlayer() throws ResourceNotFoundException {
        // Initialize player_id
        Integer player_id = 1;
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
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.of(newPlayerToLogin));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(newPlayerToLogin)).thenReturn(newPlayerToLogin);
        // assert the fields
        assertEquals(playerService.loginPlayer(player_id).get(0).getLogin(), expectedResult.getLogin());
    }

    // test Login method
    // throw ResourceNotFoundException when player_id does not exist
    @Test
    @Disabled
    public void testExceptionLoginPlayer() throws ResourceNotFoundException {
        // Initialize player_id that does not exist in DB
        Integer player_id = 1;
        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->playerService.loginPlayer(player_id),
                "Player not found by ID in DB, cannot login");
    }
    // Test Player Logout method
    // update the logout status to true when player_id exist
    @Test
    public void testLogoutPlayer() throws ResourceNotFoundException {
        // Initialize player_id
        Integer player_id = 1;
        // Initialize logout player
        Player newPlayerToLogout = new Player(1,
                "testLogoutPlayer@gmail.com",
                "testLogoutPlayer",
                true);

        // expected result after logout
        Player expectedResult = new Player(1,
                "testLogoutPlayer@gmail.com",
                "testLogoutPlayer",
                false);

        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.of(newPlayerToLogout));
        // Mock updating/saving the database
        Mockito.when(playerRepo.save(newPlayerToLogout)).thenReturn(newPlayerToLogout);
        // assert the fields
        assertEquals(playerService.logoutPlayer(player_id).get(0).getLogin(), expectedResult.getLogin());
    }

    @Test
    public void testExceptionLogoutPlayer2() {
        // Initialize player_id
        Integer player_id = 1;
        // Mock finding the Player through player_id
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->playerService.logoutPlayer(player_id),
                "Player not found by ID in DB, cannot logout");
    }

    @Test
    public void testEmailIsValid() {
        //  a valid email
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
    @Test
    public void testCheckInputsEmail1() {

        // Initialize test player with invalid email
        Player testPlayer = new Player(432,
                "william23gmail.com",
                "william23234",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkInputs(testPlayer),
                "Invalid email");

    }

    // Test that exception is raised when email is null
    @Test
    public void testCheckInputsEmail2() {

        // Initialize test player with invalid email
        Player testPlayer = new Player(54,
                "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld@gmail.com",
                "emanueld12345",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkInputs(testPlayer),
                "Email must be between 1-128 characters");
    }

    // Test that exception is raised when playername is invalid
    @Test
    public void testCheckInputsPlayername1() {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(45,
                "william123@gmail.com",
                "1111111111111111111111111111111111111111",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkInputs(testPlayer),
                "Playername must be between 1-32 characters");
    }

    // Test that exception is raised when playername is invalid
    @Test
    public void testCheckInputsPlayername2() {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(45,
                "william123@gmail.com",
                "   ",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkInputs(testPlayer),
                "Playername cannot be blank");
    }

    // Test that exception is raised when playername is null
    @Test
    public void testCheckInputsPlayername3() {

        // Initialize test player with invalid playername
        Player testPlayer = new Player(null,
                "william123@gmail.com",
                null,
                true);
        // assert exception with message
        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkInputs(testPlayer),
                "Playername cannot be blank");
    }

    // Test that exception is raised when player_id provided that is not null
    @Test
    public void testCheckPostInputs() {

        // Initialize test user with player_id that is not null
        Player testPlayer = new Player(22,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkPostPlayer(testPlayer),
                "Do not provide player_id");
    }

    // test checkUpdatePlayer method
    // can only update playername, email
    // Exception raised when player_id is provided in request body
    @Test
    public void testCheckPutInputs(){

        // Initialize test user with player_id that is null
        Player testPlayer = new Player(1,
                "Alice22@gmail.com",
                "Alice2233",
                true);

        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkUpdatePlayer(testPlayer),
                "Do not provide player_id ");
    }

    // test checkPlayerLoggedInById
    // throw exception when palyer_id is not found
    @Test
    public void testcheckPlayerLoggedInById() throws ResourceException, ResourceNotFoundException{
        Integer player_id = 1;
            Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.empty());
            Assertions.assertThrows(ResourceNotFoundException.class,
                    ()->playerService.checkPlayerLoggedInById(player_id),
                    "player_id not found");
    }

    // test checkPlayerLoggedInById
    // throw exception when palyer_id is not logged in
    @Test
    public void testcheckPlayerLoggedInById2() throws ResourceException, ResourceNotFoundException{
        Integer player_id = 1;
        Player testPlayer = new Player(1,
                "Alice22@gmail.com",
                "Alice2233",
                false);
        Mockito.when(playerRepo.findById(player_id)).thenReturn(Optional.of(testPlayer));
        Assertions.assertThrows(ResourceException.class,
                ()->playerService.checkPlayerLoggedInById(player_id),
                "player_id not logged in");
    }




/*


*/
}
