package com.example.CommunityMarket.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "item_id")
    private String itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_description")
    private String itemDescription;
    @Column(name = "item_category")
    private String itemCategory;


    public Item(String name, String description, String category) {
        this.itemName = name;
        this.itemDescription = description;
        this.itemCategory = category;
    }


    public String getId() {
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

    public void setId(String itemId) {
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
    public Item setItem(String itemId, String itemName,
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
