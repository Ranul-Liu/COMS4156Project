package com.example.CommunityMarket.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    // Data members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("player_id")
    @Column(name = "player_id")
    private Integer playerID;

    @Column(name = "playername")
    @JsonProperty("playername")
    private String playername;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "login")
    @JsonProperty("login")
    private Boolean login;

    public Player() {
        this.playerID=null;
        this.email=null;
        this.playername=null;
    }
    public Player(Integer player_id,
                String email,
                String playername,
                  Boolean login) {
        this.playerID=player_id;
        this.email=email;
        this.playername=playername;
        this.login = login;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public Integer getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public String getPlayername() {
        return this.playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
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
        return "\nPlayer { " +
                "\n\t playerID=" + playerID +
                ",\n\t playername='" + playername + '\'' +
                ",\n\t email='" + email + '\'' +
                ",\n\t login='" + login + '\'' +
                '}';
    }

}
