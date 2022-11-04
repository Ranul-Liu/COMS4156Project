package com.example.CommunityMarket.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "client_id")
    @JsonIgnore
    private Integer clientID;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "client_name")
    @JsonProperty("client_name")
    private String client_name;

    @Column(name = "company_name")
    @JsonProperty("company_name")
    private String companyName;

    public Client(){}

    public Client(Integer clientID, String email, String client_name, String companyName) {
        this.clientID = clientID;
        this.email = email;
        this.client_name = client_name;
        this.companyName = companyName;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", email='" + email + '\'' +
                ", client_name='" + client_name + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
