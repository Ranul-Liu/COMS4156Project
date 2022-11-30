/*
package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.ItemRepository;
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

    // test itemService.getByID with an existing element
    @Test
    public void testGetItemByIDExist() {
        Integer item_id = 1;
        Optional<Item> expectedResult = Optional.of(new Item(item_id,
                                                            "test item",
                                                            "This is a test item",
                                                            "test category"));
        Mockito.when(itemRepo.findById(item_id.toString())).thenReturn(expectedResult);
        Item testResult = itemService.getByID(item_id).get(0);
        assertEquals(expectedResult.get().getItemName(), testResult.getItemName());
        assertEquals(expectedResult.get().getItemId(), testResult.getItemId());
        assertEquals(expectedResult.get().getItemCategory(), testResult.getItemCategory());
        assertEquals(expectedResult.get().getItemDescription(), testResult.getItemDescription());
    }

    // test itemService.getByID with an item does not exist
    @Test(expected = IllegalArgumentException.class)
    public void testGetItemByIDNotExist() {
        Integer item_id = 1;
        Mockito.when(itemRepo.findById(item_id.toString())).thenReturn(Optional.empty());
        itemService.getByID(item_id);
    }

    // test itemService.getItemByTemplate()
    @Test
    public void testGetItemByTemplate() {
        Integer item_id = 1;
        Item expectedResult = new Item(item_id,
                "test item",
                "This is a test item",
                "test category");
        // mock find item by returning the expected result
        Mockito.when(itemRepo.findByTemplate(item_id,
                null,
                null,
                null)).thenReturn(List.of(expectedResult));

        // call itemService.getItemByTemplate() to test
        Item testResult = itemService.getItemByTemplate(1,
                null,
                null,
                null).get(0);
        assertEquals(expectedResult.getItemName(), testResult.getItemName());
        assertEquals(expectedResult.getItemId(), testResult.getItemId());
        assertEquals(expectedResult.getItemCategory(), testResult.getItemCategory());
        assertEquals(expectedResult.getItemDescription(), testResult.getItemDescription());
    }

    // test itemService.postItem() success
    @Test
    public void testPostItemSuccess() {
        Integer item_id = 1;
        // item waiting to be posted
        Item newItemToPost = new Item(item_id,
                "test item",
                "This is a test item",
                "test category");

        // mock saving the item in DB
        Mockito.when(itemRepo.save(newItemToPost)).thenReturn(new Item(item_id,
                "test item",
                "This is a test item",
                "test category"));

        //assert
        Item testResult = itemService.postItem(newItemToPost).get(0);
        assertEquals(newItemToPost.getItemName(), testResult.getItemName());
        assertEquals(newItemToPost.getItemId(), testResult.getItemId());
        assertEquals(newItemToPost.getItemCategory(), testResult.getItemCategory());
        assertEquals(newItemToPost.getItemDescription(), testResult.getItemDescription());
    }

    // test itemService.postItem() success
    @Test
    public void testUpdateItemSuccess() {
        Integer item_id = 1;
        // initialize the item BEFORE update
        Item itemToUpdate = new Item(item_id,
                "test item",
                "This is a test item",
                "test category");

        // initialize the item AFTER update
        Item expectedResult = new Item(item_id,
                "after update",
                "after update",
                "after update");

        // Mock finding the Player through player_id
        Mockito.when(itemRepo.findById(String.valueOf(itemToUpdate.getItemId()))).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(itemRepo.existsById(item_id.toString())).thenReturn(true);
        Mockito.when(itemRepo.save(expectedResult)).thenReturn(expectedResult);

        // assert the fields
        Item testResult = itemService.updateItem(itemToUpdate).get(0);
        assertEquals(itemToUpdate.getItemName(), testResult.getItemName());
        assertEquals(itemToUpdate.getItemId(), testResult.getItemId());
        assertEquals(itemToUpdate.getItemCategory(), testResult.getItemCategory());
        assertEquals(itemToUpdate.getItemDescription(), testResult.getItemDescription());
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
*/
