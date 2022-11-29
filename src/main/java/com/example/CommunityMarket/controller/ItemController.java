package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<?> getItemByTemplate(
            @RequestParam(value = "item_id", required = false) Integer item_id,
            @RequestParam(value = "item_name", required = false) String item_name,
            @RequestParam(value = "item_description", required = false) String item_description,
            @RequestParam(value = "item_category", required = false) String item_category) {

        List<Item> result = itemService.getItemByTemplate(item_id, item_name, item_description, item_category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<?> postItem(@RequestBody Item newItem) {
        List<Item> result = itemService.postItem(newItem);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/item/{item_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable Integer item_id) {
        List<Item> result = itemService.updateItem(item_id,item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*@RequestMapping(value = "/item", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@RequestParam(value = "_id") Integer user_id) {
        // delete from
        itemService.deleteItemById(item_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/


}

