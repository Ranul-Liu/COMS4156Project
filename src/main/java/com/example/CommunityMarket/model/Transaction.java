package com.example.CommunityMarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    @JsonProperty("transaction_id")
    private Integer transactionID;

//    @ManyToOne
//    @JoinColumn(name="fk_seller_id",referencedColumnName = "player_id")
    @Column(name = "seller_id")
    @JsonProperty("seller_id")
    private Integer sellerID;

//    @ManyToOne
//    @JoinColumn(name="fk_seller_id",referencedColumnName = "player_id")
//    private Player seller;

    @Column(name = "buyer_id")
    @JsonProperty("buyer_id")
    private Integer buyerID;

    @Column(name = "item_id")
    @JsonProperty("item_id")
    private Integer itemID;

    @Column(name = "initial_price")
    @JsonProperty("initial_price")
    private int price;




    @Column(name = "post_time")
    @JsonProperty("post_time")
    private LocalDateTime postTime;


    @Column(name = "close_time")
    @JsonProperty("close_time")
    private LocalDateTime closeTime;


    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "open")
    private boolean open;

    @Column(name = "accept")
    private boolean accept;

    public Transaction(){}

    public Transaction(Integer itemID, int price, int quantity) {
        this.itemID = itemID;
        this.price = price;
        this.quantity = quantity;
    }

    public Transaction(Integer transactionID, Integer sellerID, Integer buyerID, Integer itemID, int price, LocalDateTime postTime, LocalDateTime closeTime, int quantity, boolean open, boolean accept) {
        this.transactionID = transactionID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.itemID = itemID;
        this.price = price;
        this.postTime = postTime;
        this.closeTime = closeTime;
        this.quantity = quantity;
        this.open = open;
        this.accept = accept;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    public Integer getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Integer buyerID) {
        this.buyerID = buyerID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
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
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", sellerID='" + sellerID + '\'' +
                ", buyerID='" + buyerID + '\'' +
                ", itemID=" + itemID +
                ", price=" + price +
                ", postTime=" + postTime +
                ", closeTime=" + closeTime +
                ", quantity=" + quantity +
                ", open=" + open +
                ", accept=" + accept +

                '}';
    }
}
