package com.example.CommunityMarket.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "itemId")
    private String id;
    @Column(name = "itemName")
    private String name;
    @Column(name = "itemDescription")
    private String description;
    @Column(name = "itemCategory")
    private String category;


    public Item(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Item setItem(String id, String name,
                        String description, String category
                        ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && name.equals(item.name) && description.equals(item.description) && category.equals(item.category);
    }
    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
