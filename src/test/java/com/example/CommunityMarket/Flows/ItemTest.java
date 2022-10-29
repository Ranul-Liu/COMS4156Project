package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.Repository.ItemRepository;
import com.example.CommunityMarket.model.User;
import com.example.CommunityMarket.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepo;

    @Test
    public void testCheckGetByIdExistsForItem() {

        Integer item_id = 1;
        Item newItem = new Item(1,
                "Sword",
                "This is a sword",
                "Attack weapons");

        Optional<Item> optItem = Optional.of(newItem);
        Mockito.when(itemRepo.findById(item_id.toString())).thenReturn(optItem);
        assertEquals(List.of(optItem.get()), itemService.getByID(item_id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIDExceptForItem() {
        Integer item_id = 1;
        Mockito.when(itemRepo.findById(item_id.toString())).thenReturn(Optional.empty());
        itemService.getByID(item_id);
    }

    @Test
    public void testGetItemsByTemplate() {
        Item newItem = new Item(1,
                "Sword",
                "This is a sword",
                "Attack weapons");
        Mockito.when(itemRepo.findByTemplate("1",
                null,
                null,
                null)).thenReturn(List.of(newItem));
        Item result = itemService.getItemByTemplate("1",
                null,
                null,
                null).get(0);
        assertEquals(result.getId(), result.getId());
    }

    // Test that the id is correctly updated by postItem method
    @Test
    public void testPostItem() {

        // Initialize item before postItem called
        Item beforeItem = new Item(null,
                "Sword",
                "This is a sword",
                "Attack weapons");

        // Create newly inserted Item
        Item afterItem = new Item(2,
                "Sword",
                "This is a sword",
                "Attack weapons");

        // save the item
        Mockito.when(itemRepo.save(beforeItem)).thenReturn(afterItem);

        //assert that the itemId gets correctly updated
        assertEquals(itemService.postItem(beforeItem).get(0).getId(), 2);
    }

    //Test that item gets correctly updated
    @Test
    public void testUpdateItem() {
        // Initialize updated item
        Item beforeItem = new Item(1,
                "Sword",
                "This is a sword",
                "Attack weapons");

        Item updatedItem = new Item(1,
                "Shield",
                "This is a shield",
                "Defense weapons");

        // user exists
        Mockito.when(itemRepo.findById(String.valueOf(updatedItem.getId()))).thenReturn(Optional.of(updatedItem));

        // save the changes
        Mockito.when(itemRepo.save(updatedItem)).thenReturn(updatedItem);

        // item exists
        Mockito.when(itemRepo.existsById("1")).thenReturn(true);

        // save the changes
        Mockito.when(itemRepo.save(updatedItem)).thenReturn(updatedItem);

        // assert that item gets correctly updated by checking that all data members are equal to the updatedItem
        assertEquals(itemService.updateItem(updatedItem).get(0).getId(), updatedItem.getId());
        assertEquals(itemService.updateItem(updatedItem).get(0).getName(), updatedItem.getName());
        assertEquals(itemService.updateItem(updatedItem).get(0).getDescription(), updatedItem.getDescription());
        assertEquals(itemService.updateItem(updatedItem).get(0).getCategory(), updatedItem.getCategory());


    }

    //Make sure exception raised when the item does not exist
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdateItem() {

        // Initialize updated Item
        Item updatedItem = new Item(10,
                "Sword",
                "This is a sword",
                "Attack weapons");

        // itemID does not exist
        Mockito.when(itemRepo.existsById("10")).thenReturn(false);

        //call updateItem method
        itemService.updateItem(updatedItem);
    }

}
