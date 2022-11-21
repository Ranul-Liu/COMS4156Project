package com.example.CommunityMarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table (name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    @JsonProperty("transaction_id")
    private Integer transactionID;

    @Column(name = "seller_id")
    @JsonProperty("seller_id")
    private String sellerID;

    @Column(name = "buyer_id")
    @JsonProperty("buyer_id")
    private String buyerID;

    @Column(name = "item_id")
    @JsonProperty("item_id")
    private Integer itemID;

    @Column(name = "price")
    @JsonProperty("price")
    private int price;


    @Column(name = "post_time")
    private Date postTime;

    @Column(name = "close_time")
    private Date closeTime;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    @Column(name = "open")
    private boolean open;

    @Column(name = "accept")
    private boolean accept;


    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
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

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
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
