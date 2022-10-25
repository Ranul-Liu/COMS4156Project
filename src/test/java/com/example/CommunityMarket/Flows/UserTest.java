package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.model.User;
import com.example.CommunityMarket.Repository.UserRepository;
import com.example.CommunityMarket.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepo;

    // Test that the username is correctly updated by postUser method
    @Test
    public void testPostUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize user before postUser called
        User beforeUser = new User(null,
                "selena12@gmail.com",
                "def666");

        // Create newly inserted User
        User afterUser = new User(123,
                "selena12@gmail.com",
                "def666");

        // save the user
        Mockito.when(userRepo.save(beforeUser)).thenReturn(afterUser);

        //assert that the username gets correctly updated
        assertEquals(userService.postUser(beforeUser).get(0).getUsername(), "def666");
    }

    //Test that user gets correctly updated
    @Test
    public void testUpdateUser() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated user
        User updatedUser = new User(123,
                "emanueld@gmail.com",
                "123abc");

        // user exists
        Mockito.when(userRepo.existsById(updatedUser.getUserID().toString())).thenReturn(true);

        // save the changes
        Mockito.when(userRepo.save(updatedUser)).thenReturn(updatedUser);

        // assert that user gets correctly updated by checking that all data members are equal to the updatedUser
        assertEquals(userService.updateUser(updatedUser).get(0).getUserID(), updatedUser.getUserID());
        assertEquals(userService.updateUser(updatedUser).get(0).getEmail(), updatedUser.getEmail());
        assertEquals(userService.updateUser(updatedUser).get(0).getUsername(), updatedUser.getUsername());
    }

    //Make sure exception raised when the user does not exist
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdateUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated user
        User updatedUser = new User(22,
                "Alice22@gmail.com",
                "Alice2233");

        // userID does not exist
        Mockito.when(userRepo.existsById(updatedUser.getUserID().toString())).thenReturn(false);

        //call updateUser method
        userService.updateUser(updatedUser);
    }

    // Make sure that delete raises exception when user not found
    /*@Test(expected = IllegalArgumentException.class)
    public void testExceptionDeleteUserById() throws {

    }*/

    @Test
    public void testIfInvalidEmail() {

        // Initialize a string containing > 128 chars
        String test = "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyz";

        // assert that checkIfInvalid returns true
        assertTrue(userService.checkIfInvalid(test));


        //  Change the string to within the 128 limit but containing only blanks
        test = "   ";

        // assert that checkIfInvalid returns true
        assertTrue(userService.checkIfInvalid(test));


        // Change the String to a valid string
        test = "This is a valid string.";

        //assert that checkIfInvalid returns false
        assertFalse(userService.checkIfInvalid(test));

    }

    // Test that exception is raised when email is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsEmail1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid email
        User testUser = new User(432,
                "william23gmail.com",
                "william23234");

        userService.checkInputs(testUser);
    }

    // Test that exception is raised when email is null
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsEmail2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid email
        User testUser = new User(54,
                "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld" +
                        "emanueldemanueldemanueldemanueldemanueldemanueld@gmail.com",
                "emanueld12345");

        userService.checkInputs(testUser);
    }

    // Test that exception is raised when username is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsUsername1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid username
        User testUser = new User(45,
                "william123@gmail.com",
                "william123");

        userService.checkInputs(testUser);
    }

    // Test that exception is raised when username is null
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsUsername2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid username
        User testUser = new User(null,
                "william123@gmail.com",
                "william123");

        userService.checkInputs(testUser);
    }

    @Test
    public void testCheckGetByIdExists() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        User newUser = new User(77,
                "william23gmail.com",
                "william23234");
        Integer user_id = newUser.getUserID();

        Optional<User> optUser = Optional.of(newUser);
        Mockito.when(userRepo.findById(user_id.toString())).thenReturn(optUser);
        assertEquals(List.of(optUser.get()), userService.getByID(user_id));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckGetByIdNotExists() {
        Integer user_id = 1;
        Optional<User> optUser = Optional.empty();
        Mockito.when(userRepo.findById(user_id.toString())).thenReturn(optUser);
        userService.getByID(user_id);

    }

    @Test
    public void testGetUsersByTemplate() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        User newUser = new User(984,
                "william123@gmail.com",
                "william123");

        Mockito.when(userRepo.findByTemplate(null,
                "william123@gmail.com", "william123")).thenReturn(List.of(newUser));
        User result = userService.getUserByTemplate(null,
                "william123@gmail.com", "william123").get(0);
        assertEquals(result.getUserID(), result.getUserID());

    }





}
