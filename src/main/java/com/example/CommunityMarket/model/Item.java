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

    public Item(Integer itemId, String itemName, String itemDescription, String itemCategory) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }

    public Item() {

    }

    public Integer getId() {
        return itemId;
    }

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return itemDescription;
    }

    public String getCategory() {
        return itemCategory;
    }

    public void setId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
    public Item setItem(Integer itemId, String itemName,
                        String itemDescription, String itemCategory
                        ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        return this;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id='" + itemId + '\'' +
                ", name='" + itemName + '\'' +
                ", description='" + itemDescription + '\'' +
                ", category='" + itemCategory + '\'' +
                '}';
    }
}
