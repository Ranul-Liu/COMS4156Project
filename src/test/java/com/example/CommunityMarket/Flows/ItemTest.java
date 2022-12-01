
package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.ItemRepository;
import com.example.CommunityMarket.service.ItemService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepo;

    // test itemService.getByID with an existing element
    @Test
    public void testGetItemByIDExist() throws ResourceNotFoundException {
        Integer item_id = 1;
        Optional<Item> expectedResult = Optional.of(new Item(item_id,
                                                            "test item",
                                                            "This is a test item",
                                                            "test category"));
        Mockito.when(itemRepo.findById(item_id)).thenReturn(expectedResult);
        Item testResult = itemService.getByID(item_id).get(0);
        assertEquals(expectedResult.get().getItemName(), testResult.getItemName());
        assertEquals(expectedResult.get().getItemId(), testResult.getItemId());
        assertEquals(expectedResult.get().getItemCategory(), testResult.getItemCategory());
        assertEquals(expectedResult.get().getItemDescription(), testResult.getItemDescription());
    }

    // test itemService.getByID with an item does not exist
    // exception raised when item_id does not exist
    @Test
    public void testGetItemByIDNotExist() {
        Integer item_id = 1;
        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()->itemService.getByID(item_id),
                "Item not found by ID in DB."
                );
    }

    // test itemService.getItemByTemplate()
    @Test
    public void testGetItemByTemplateExist() throws ResourceNotFoundException {
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

    // test itemService.updateItem() success
    @Test
    public void testUpdateItemSuccess() throws ResourceNotFoundException {
        Integer item_id = 1;
        // initialize the item BEFORE update
        Item itemBeforeUpdate = new Item(item_id,
                "test item",
                "This is a test item",
                "test category");
        // initialize the item for request body
        Item itemForUpdate = new Item(item_id,
                "after update",
                "after update",
                "after update");

        // initialize the expected response AFTER update
        Item expectedResult = new Item(item_id,
                "after update",
                "after update",
                "after update");

        // Mock finding the Player through player_id
        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.of(itemBeforeUpdate));
        // Mock updating/saving the database
        Mockito.when(itemRepo.save(itemForUpdate)).thenReturn(itemForUpdate);

        // assert the fields
        Item testResult = itemService.updateItem(item_id,itemForUpdate).get(0);
        assertEquals(expectedResult.getItemName(), testResult.getItemName());
        assertEquals(expectedResult.getItemId(), testResult.getItemId());
        assertEquals(expectedResult.getItemCategory(), testResult.getItemCategory());
        assertEquals(expectedResult.getItemDescription(), testResult.getItemDescription());
    }

    // Test UpdateItem
    // Exception Raised when item_id is not found in DB
    @Test
    public void testExceptionUpdateItem() {
        Integer item_id = 1;
        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()->itemService.deleteItemById(item_id),
                "Item not found by ID in DB, cannot update");
    }

    // Test DeleteItemById Succeed
    @Test
    public void testDeleteItemByIdSucceed() throws ResourceNotFoundException {
        Integer item_id = 1;
        Item itemToDelete = new Item(1,
                "test item",
                "This is a test item",
                "test category"
                );

        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.of(itemToDelete));
        itemRepo.deleteById(item_id);
        Mockito.verify(itemRepo).deleteById(item_id);
    }

    // Test DeleteItemById
    // Exception Raised when item_id is not found in DB
    @Test
    public void testExceptionDeleteItemById() {
        Integer item_id = 1;
        Mockito.when(itemRepo.findById(item_id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                ()->itemService.deleteItemById(item_id),
                "Item not found by ID in DB, cannot delete");
    }

    // Test checkPostItemInputs
    // Exception thrown when item_id is provided when post item
    @Test
    public void testExceptionCheckPostItemInputs() {

        Item item = new Item(1,
                "test item",
                "This is a test item",
                "test category"
        );
        Assertions.assertThrows(ResourceException.class,
                ()->itemService.checkPostItemInputs(item),
                "Please do not provide item_id");
    }

    // Test checkPostItemInputs
    // Exception thrown when item_name is not provided
    @Test
    public void testExceptionCheckPostItemInputs2() {

        Item item = new Item(1,
                null,
                "This is a test item",
                "test category"
        );
        Assertions.assertThrows(ResourceException.class,
                ()->itemService.checkPostItemInputs(item),
                "Please provide item name");
    }

    // Test checkPostItemInputs
    // Exception thrown when item_category is not provided
    @Test
    public void testExceptionCheckPostItemInputs3() {

        Item item = new Item(1,
                "test item",
                "test description",
                null
        );
        Assertions.assertThrows(ResourceException.class,
                ()->itemService.checkPostItemInputs(item),
                "Please provide item category");
    }
    // Test checkPostItemInputs
    // Exception thrown when item_description is not provided
    @Test
    public void testExceptionCheckPostItemInputs4() {

        Item item = new Item(1,
                "test item",
                null,
                "test category"
        );
        Assertions.assertThrows(ResourceException.class,
                ()->itemService.checkPostItemInputs(item),
                "Please provide item description");
    }

}

