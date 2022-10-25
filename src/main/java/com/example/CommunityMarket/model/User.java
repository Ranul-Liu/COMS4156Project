package com.example.CommunityMarket.model;

//import java.util.Objects;
//import java.util.UUID;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.util.regex.Pattern;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;

//public class test {
//
//    private UUID userID;
//    private String email;
//    private String username;
//    private String password;
//    private boolean logInState;
//    private Connection connection;
//    private int usernameLength = 16;
//    private int passwordLength = 16;
//    private String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
//
//    public User(String username, String email, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.logInState = false;
//        setUserID();
//        setPassword(password);
//        loadDriver();
//    }
//
//    private void setConnection() {
//        try {
//            this.connection =
//                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
//                            "user=minty&password=greatsqldb");
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//    }
//
//    private void loadDriver() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
//
//    public void postUser(UUID userID, String username, String password) throws SQLException {
//        if (username.length() > this.usernameLength) {
//            throw new IllegalArgumentException("username too long");
//        }
//        if (password.length() > this.passwordLength) {
//            throw new IllegalArgumentException("password too long");
//        }
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(
//                String.format("INSERT INTO Users (userID, username, password) "
//                        + "values (%s, %s, %s)", userID, username, password));
//        statement.close();
//    }
//
//    public void postUsername(String username) throws SQLException, IllegalAccessException {
//        if (!this.logInState) {
//            throw new IllegalAccessException("not logged in");
//        }
//        if (username.length() > this.usernameLength) {
//            throw new IllegalArgumentException("username too long");
//        }
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(
//                String.format("UPDATE Users username = %s where userID = %s", username, this.userID));
//        statement.close();
//    }
//
//    public void postPassword(String existingPassword, String password) throws SQLException, IllegalAccessException {
//        if (!this.password.equals(existingPassword)) {
//            throw new IllegalAccessException("invalid password");
//        }
//        if (password.length() > this.passwordLength) {
//            throw new IllegalArgumentException("password too long");
//        }
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(
//                String.format("UPDATE Users password = %s where userID = %s", password, this.userID));
//        statement.close();
//    }
//
//    public void postEmail(String existingPassword, String email) throws SQLException, IllegalAccessException {
//        if (!this.password.equals(existingPassword)) {
//            throw new IllegalAccessException("invalid password");
//        }
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(
//                String.format("UPDATE Users email = %s where userID = %s", email, this.userID));
//        statement.close();
//    }
//
//    public ResultSet getProfile(String userID) throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(String.format(
//                "select userID, username from Users where userID = %s", userID));
//        statement.close();
//        return resultSet;
//    }
//
//    public UUID getUserID() {
//        if (this.userID == null) {
//            throw new NullPointerException("userID is null");
//        }
//        else {
//            return this.userID;
//        }
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//
//    // private method to initialize a GUID
//    // should only call once
//    private void setUserID() {
//        this.userID = UUID.randomUUID();
//    }
//
//    public void setEmail(String email) {
//        boolean isValid = Pattern.compile(regexPattern)
//                .matcher(email)
//                .matches();
//        if (!isValid) {
//            throw new IllegalArgumentException("wrong email format");
//        }
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        if (password.length() > this.passwordLength) {
//            throw new IllegalArgumentException("password too long");
//        }
//        this.password = password;
//    }
//
//    public void logIn(String password) {
//        if (this.password.equals(password)) {
//            this.logInState = true;
//        }
//        else {
//            throw new IllegalArgumentException("invalid password");
//        }
//    }
//
//    public void logOut() {
//        if (this.logInState) {
//            this.logInState = false;
//        }
//        else {
//            throw new IllegalArgumentException("invalid action");
//        }
//    }
//
//    public boolean equals(User target) {
//        if (target == null) return false;
//        return this.userID == target.userID;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userID, email, username);
//    }
//}


@Entity
@Table(name = "user")
public class User {

    // Data members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    public User() {
        this.userID=null;
        this.email=null;
        this.username=null;
    }
    public User(Integer user_id,
                String email,
                String username) {
        this.userID=user_id;
        this.email=email;
        this.username=username;
    }

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //  Return class as JSON String
    @Override
    public String toString() {
        return "\nUser { " +
                "\n\t userID=" + userID +
                ",\n\t username='" + username + '\'' +
                ",\n\t email='" + email + '\'' +
                '}';
    }

}
