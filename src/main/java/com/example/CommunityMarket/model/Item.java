package com.example.CommunityMarket.model;

import java.util.Objects;

public class Item {


    private String id;
    private String name;
    private String description;
    private String category;

    public Item(String id, String name, String description, String category) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && name.equals(item.name) && description.equals(item.description) && category.equals(item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category);
    }
}
