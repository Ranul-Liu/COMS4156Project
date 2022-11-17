package com.example.CommunityMarket.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

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

    @Column(name = "login")
    @JsonProperty("login")
    private Integer login;

    public User() {
        this.userID=null;
        this.email=null;
        this.username=null;
    }
    public User(Integer user_id,
                String email,
                String username,
                Integer login) {
        this.userID=user_id;
        this.email=email;
        this.username=username;
        this.login = login;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
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
                ",\n\t login='" + login + '\'' +
                '}';
    }

}
