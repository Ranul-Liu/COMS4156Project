package com.example.CommunityMarket.service;

import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
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
    public List<Item> getByID(Integer itemID) throws ResourceNotFoundException{
        Optional<Item> result = itemRepo.findById(itemID);
        if (result.isPresent()) {
            Item itemResult = result.get();
            return List.of(itemResult);
        } else {
            throw new ResourceNotFoundException("Item not found by ID in DB.");
        }
    }
    //get operation
    public List<Item> getItemByTemplate(Integer item_id,
                                        String item_name,
                                        String item_description,
                                        String item_category) throws ResourceNotFoundException {
        List<Item> result = itemRepo.findByTemplate(item_id,item_name,item_description,item_category);
        if (!result.isEmpty()){
            return result;
        } else {
            throw new ResourceNotFoundException("Item not found in DB");
        }

    }

    //post operation
    public List<Item> postItem(Item item) {

        Item result = itemRepo.save(item);
        return List.of(result);
    }

    //put operation
    public List<Item> updateItem(Integer item_id, Item updateditem) throws ResourceNotFoundException {
        Optional<Item> result = itemRepo.findById(item_id);
        if(result.isPresent()){
            Item item = result.get();
            item.setItemName(updateditem.getItemName());
            item.setItemCategory(updateditem.getItemCategory());
            item.setItemDescription(updateditem.getItemDescription());
            itemRepo.save(updateditem);
            return List.of(item);
        } else {
            throw new ResourceNotFoundException("Item not found by ID in DB, cannot update");
        }
    }

    //delete operation
    public void deleteItemById(Integer item_id) throws ResourceNotFoundException {
        Optional<Item> result = itemRepo.findById(item_id);
        if (result.isPresent()) {
            itemRepo.deleteById(item_id);
        } else {
            throw new ResourceNotFoundException("Item not found by ID in DB, cannot delete");
        }
    }


    //check inputs for the item
    public void checkPostItemInputs (Item item) throws ResourceException {
        if (item.getItemId() != null){
            throw new ResourceException("Please do not provide item_id");
        }
        if (item.getItemName() == null) {
            throw new ResourceException("Please provide item name");
        }
        if (item.getItemCategory() == null) {
            throw new ResourceException("Please provide item category");
        }
        if (item.getItemDescription() == null) {
            throw new ResourceException("Please provide item description");
        }

     }

}
