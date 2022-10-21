package com.example.CommunityMarket.service;

import com.example.CommunityMarket.Repository.ItemRepository;
import com.example.CommunityMarket.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //find by id
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
