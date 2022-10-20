package com.example.CommunityMarket.model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionID;
    private String sellerID;
    private String buyerID;
    private String itemID;
    private int price;
    private LocalDateTime postTime;
    private int quantity;
    private boolean isOpen;
    private LocalDateTime closeTime;

    public Transaction(String transactionID, String sellerID, String itemID, int price) {
        this.transactionID = transactionID;
        this.sellerID = sellerID;
        this.itemID = itemID;
        this.price = price;
    }

    public String getTransactionID() {
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

    public void setTransactionID(String transactionID) {
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
}
