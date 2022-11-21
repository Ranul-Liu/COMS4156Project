package com.example.CommunityMarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("item_id")
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "item_name")
    @JsonProperty("item_name")
    private String itemName;
    @Column(name = "item_description")
    @JsonProperty("item_description")
    private String itemDescription;
    @Column(name = "item_category")
    @JsonProperty("item_category")
    private String itemCategory;

    //Default Constructor
    public Item() {
        this.itemId=null;
        this.itemName=null;
        this.itemDescription=null;
        this.itemCategory=null;
    }
    public Item(Integer itemId, String itemName, String itemDescription, String itemCategory) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                '}';
    }
}
