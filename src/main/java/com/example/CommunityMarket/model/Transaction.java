package com.example.CommunityMarket.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table (name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionID;
    @Column(name = "seller_id")
    private String sellerID;

    @Column(name = "buyer_id")
    private String buyerID;
    @Column(name = "item_id")
    private String itemID;
    @Column(name = "price")
    private int price;
    @Column(name = "postTime")
    private LocalDateTime postTime;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "isOpen")
    private boolean isOpen;
    @Column(name = "closeTime")
    private LocalDateTime closeTime;



    public Transaction(Integer transactionID, String sellerID, String itemID, int price) {
        this.transactionID = transactionID;
        this.sellerID = sellerID;
        this.itemID = itemID;
        this.price = price;
    }

    public Transaction() {

    }


    public Integer getTransactionID() {
        return transactionID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", sellerID='" + sellerID + '\'' +
                ", buyerID='" + buyerID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", price=" + price +
                ", postTime=" + postTime +
                ", quantity=" + quantity +
                ", isOpen=" + isOpen +
                ", closeTime=" + closeTime +
                '}';
    }
}
