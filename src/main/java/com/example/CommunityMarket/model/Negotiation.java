package com.example.CommunityMarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table (name = "negotiation")
public class Negotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private Integer negotiation_id;

    @Column(name = "buyer_id")
    private String buyer_id;

    @Column(name = "post_time")
    private LocalDateTime post_time;

    @Column(name = "close_time")
    private LocalDateTime close_time;

    @Column(name = "price")
    @JsonProperty("price")
    private int price;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "open")
    private boolean open;

    @Column(name = "accept")
    private boolean accept;

    @ManyToOne
    @JoinColumn(name="fk_transaction_id",referencedColumnName = "transaction_id")
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Integer getNegotiation_id() {
        return negotiation_id;
    }

    public void setNegotiation_id(Integer negotiation_id) {
        this.negotiation_id = negotiation_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public LocalDateTime getPost_time() {
        return post_time;
    }

    public void setPost_time(LocalDateTime post_time) {
        this.post_time = post_time;
    }

    public LocalDateTime getClose_time() {
        return close_time;
    }

    public void setClose_time(LocalDateTime close_time) {
        this.close_time = close_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return "Negotiation{" +
                "negotiation_id=" + negotiation_id +
                ", buyer_id=" + buyer_id +
                ", post_time=" + post_time +
                ", close_time=" + close_time +
                ", price=" + price +
                ", quantity=" + quantity +
                ", open=" + open +
                ", accept=" + accept +
                '}';
    }
}
