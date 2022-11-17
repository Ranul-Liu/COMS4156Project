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

    // test postUser method
    @Test
    public void testPostUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize a User object to save into the database
        // user_id = null because it will be auto-generated
        User newUserToPost = new User(null,
                "testPostUser@gmail.com",
                "testPostUser",
                1);

        // Mock saving the User object
        Mockito.when(userRepo.save(newUserToPost)).thenReturn(new User(1,
                "testPostUser@gmail.com",
                "testPostUser",
                1));

        //assert that the return value of Post are as expected
        User resultOfPostUser = userService.postUser(newUserToPost).get(0);
        assertEquals(resultOfPostUser.getUsername(), "testPostUser");
        assertEquals(resultOfPostUser.getEmail(), "testPostUser@gmail.com");
        assertEquals(resultOfPostUser.getLogin(), 1);
        // did not assert user_id because it is auto-generated
    }


    // test updateUser method
    @Test
    public void testUpdateUser() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated user
        User newUserToUpdate = new User(1,
                "testUpdateUser@gmail.com",
                "testUpdateUser",
                1);

        // expected result after update
        User expectedResult = new User(1,
                "emanueld@gmail.com",
                "123abc",
                0);

        // Mock finding the User through user_id
        Mockito.when(userRepo.findById(String.valueOf(newUserToUpdate.getUserID()))).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(userRepo.save(expectedResult)).thenReturn(expectedResult);

        User resultOfUpdateUser = userService.updateUser(expectedResult).get(0);
        // assert the fields
        assertEquals(resultOfUpdateUser.getUserID(), expectedResult.getUserID());
        assertEquals(resultOfUpdateUser.getEmail(), expectedResult.getEmail());
        assertEquals(resultOfUpdateUser.getUsername(), expectedResult.getUsername());
        assertEquals(resultOfUpdateUser.getLogin(), expectedResult.getLogin());
    }

    //Make sure exception raised when the user does not exist
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdateUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize updated user
        User updatedUser = new User(22,
                "Alice22@gmail.com",
                "Alice2233",
                1);

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
    public void testEmailIsValid() {
        //  string containing only whitespaces
        String test = "testEmailIsValid@gmail.com";

        // assert email is not empty and is within the length limit
        assertTrue(userService.checkEmailLength(test));
        // assert email is in the right form
        assertTrue(userService.isValidEmail(test));
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
        assertFalse(userService.checkEmailLength(test));
        // assert email is in the right form
        assertFalse(userService.isValidEmail(test));
    }

    @Test
    public void testEmptyEmailIsInvalid() {
        //  string containing only whitespaces
        String test = "   ";

        // assert email is not empty and is within the length limit
        assertFalse(userService.checkEmailLength(test));
        // assert email is in the right form
        assertFalse(userService.isValidEmail(test));
    }

    // Test that exception is raised when email is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsEmail1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid email
        User testUser = new User(432,
                "william23gmail.com",
                "william23234",
                1);

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
                "emanueld12345",
                1);

        userService.checkInputs(testUser);
    }

    // Test that exception is raised when username is invalid
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsUsername1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid username
        User testUser = new User(45,
                "william123@gmail.com",
                "1111111111111111111111111111111111111111",
                1);

        userService.checkInputs(testUser);
    }

    // Test that exception is raised when username is null
    @Test(expected = IllegalArgumentException.class)
    public void testCheckInputsUsername2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Initialize test user with invalid username
        User testUser = new User(null,
                "william123@gmail.com",
                null,
                1);

        userService.checkInputs(testUser);
    }

    @Test
    public void testCheckGetByIdExists() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        User newUser = new User(77,
                "william23gmail.com",
                "william23234",
                1);
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
                "william123",
                1);

        Mockito.when(userRepo.findByTemplate(null,
                "william123@gmail.com", "william123",1)).thenReturn(List.of(newUser));
        User result = userService.getUserByTemplate(null,
                "william123@gmail.com", "william123",1).get(0);
        assertEquals(result.getUserID(), result.getUserID());

    }





}
