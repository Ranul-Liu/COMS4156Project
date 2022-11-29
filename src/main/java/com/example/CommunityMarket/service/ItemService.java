package com.example.CommunityMarket.service;

import com.example.CommunityMarket.repository.ItemRepository;
import com.example.CommunityMarket.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepo;

    //get by ID
    public List<Item> getByID(Integer itemID) {
        Optional<Item> result = itemRepo.findById(itemID);
        if (result.isPresent()) {
            Item itemResult = result.get();
            return List.of(itemResult);
        } else {
            throw new IllegalArgumentException("Item not found by ID in DB.");
        }
    }
    //get operation
    public List<Item> getItemByTemplate(Integer item_id,
                                        String item_name,
                                        String item_description,
                                        String item_category){
        return itemRepo.findByTemplate(item_id,item_name,item_description,item_category);
    }

    //post operation
    public List<Item> postItem(Item item) {

        Item result = itemRepo.save(item);
        return List.of(result);
    }

    //put operation
    public List<Item> updateItem(Integer item_id, Item updateditem) throws IllegalArgumentException {
        Optional<Item> result = itemRepo.findById(item_id);
        if(result.isPresent()){
            Item item = result.get();
            item.setItemName(updateditem.getItemName());
            item.setItemCategory(updateditem.getItemCategory());
            item.setItemDescription(updateditem.getItemDescription());
            return List.of(item);
        } else {
            throw new IllegalArgumentException("Item not found by ID in DB, cannot update");
        }
    }

    // delete operation
    /*public void deleteItemById(Item item) {
        if (getByID(item.getId()).size() >= 1) {
            itemRepo.deleteById(item.getId());
        } else {
            throw new IllegalArgumentException("Resource not found in DB, cannot delete");
        }
    }*/


    //check inputs for the item
    /*public void checkItemInputs (Item item) throws ResourceException {
        if (item.getName() == null) {
            throw new ResourceException("Please provide item name");
        }
        if (item.getDescription() == null) {
            throw new ResourceException("Please provide item description");
        }
        if (item.getCategory() == null) {
            throw new ResourceException("Please provide item category");
        }

     }*/

}
