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
    @Autowired
    ItemRepository itemRepo;

    //get operation
    public List<Item> getItemByTemplate(String item_id,
                                        String item_name,
                                        String item_description,
                                        String item_category){
        return itemRepo.findByTemplate(item_id,item_name,item_description,item_category);

    }


}
