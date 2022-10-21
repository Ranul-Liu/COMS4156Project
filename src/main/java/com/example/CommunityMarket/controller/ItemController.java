package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<?> getItemByTemplate(
            @RequestParam(value = "item_id", required = false) String item_id,
            @RequestParam(value = "item_name", required = false) String item_name,
            @RequestParam(value = "item_description", required = false) String item_description,
            @RequestParam(value = "item_category", required = false) String item_category) {


        // verify userID and logged in?

        // get results
        List<Item> result = itemService.getItemByTemplate(item_id, item_name, item_description, item_category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<?> postItem(@RequestBody Item newItem) {
        List<Item> result = itemService.postItem(newItem);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@RequestBody Item newItem) {
        List<Item> result = itemService.updateItem(newItem);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*@RequestMapping(value = "/item", method = RequestMethod.DELETE)
    public ResponseEntity<?> postItem(@RequestBody Item newItem) {
        List<Item> result = itemService.deleteItem(newItem);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/

}

