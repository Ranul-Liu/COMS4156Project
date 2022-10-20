package com.example.CommunityMarket.model;

import java.util.Objects;
import java.util.UUID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class User {

    private UUID userID;
    private String email;
    private String username;
    private String password;
    private boolean logInState;
    private Connection connection;

    public User(String username, String email, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.username = username;
        this.email = email;
        this.password = password;
        this.logInState = false;
        setUserID();
        setPassword(password);
        loadDriver();
    }

    private void setConnection() {
        try {
            this.connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=minty&password=greatsqldb");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void loadDriver() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void postUser() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
                String.format("INSERT INTO Users (userID, username, password) "
                        + "values (%s, %s, %s)", this.userID, this.username, this.password));
        statement.close();
    }

    public UUID getUserID() {
        if (this.userID == null) {
            throw new NullPointerException("userID is null");
        }
        else {
            return this.userID;
        }
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    // private method to initialize a GUID
    // should only call once
    private void setUserID() {
        this.userID = UUID.randomUUID();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logIn(String password) {
        if (this.password.equals(password)) {
            this.logInState = true;
        }
        else {
            throw new IllegalArgumentException("invalid password");
        }
    }

    public void logOut() {
        if (this.logInState) {
            this.logInState = false;
        }
        else {
            throw new IllegalArgumentException("invalid action");
        }
    }

    public boolean equals(User target) {
        if (target == null) return false;
        return this.userID == target.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, email, username);
    }
}
