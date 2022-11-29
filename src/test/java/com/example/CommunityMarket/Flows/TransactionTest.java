package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.repository.TransactionRepository;
import com.example.CommunityMarket.service.TransactionService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {
    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepo;

    // test transactionService.getByID() exist
    @Test
    public void testGetTransactionByIdExist() {
        Integer transaction_id = 1;
        Integer seller_id = 1;
        LocalDateTime date = LocalDateTime.now();
        Integer item_id = 1;
        int price = 100;
        int quantity = 1;
        boolean open = true, accept = false;
        Transaction expectedResult = new Transaction(
                transaction_id,
                seller_id,
                "buyer",
                item_id,
                price,
                date,
                date,
                quantity,
                open,
                accept,
                null
        );

        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.of(expectedResult));

        // assert
        Transaction testResult = transactionService.getByID(item_id).get(0);
        assertEquals(expectedResult.getTransactionID(), testResult.getTransactionID());
        assertEquals(expectedResult.getBuyerID(), testResult.getBuyerID());
        assertEquals(expectedResult.getItemID(), testResult.getItemID());
        assertEquals(expectedResult.getPrice(), testResult.getPrice());
        assertEquals(expectedResult.getQuantity(), testResult.getQuantity());
        assertEquals(expectedResult.getPostTime(), testResult.getPostTime());
        assertEquals(expectedResult.getCloseTime(), testResult.getCloseTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTransactionByIDNotExist() {
        Integer transaction_id = 1;
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.empty());
        transactionService.getByID(transaction_id);
    }

    // test transactionService.addTransaction()
    @Test
    public void testPostTransaction() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Initialize transaction before post method called
        Integer transaction_id = 1;
        Integer seller_id = 1;
        LocalDateTime date = LocalDateTime.now();
        Integer item_id = 1;
        int price = 100;
        int quantity = 1;
        boolean open = true, accept = false;
        Transaction newTransactionToPost = new Transaction(
                transaction_id,
                seller_id,
                "buyer",
                item_id,
                price,
                date,
                date,
                quantity,
                open,
                accept,
                null
        );

        // mock saving in DB
        Mockito.when(transactionRepo.save(newTransactionToPost)).thenReturn(new Transaction(
                transaction_id,
                seller_id,
                "buyer",
                item_id,
                price,
                date,
                date,
                quantity,
                open,
                accept,
                null
        ));

        // assert
        Transaction testResult = transactionService.addTransaction(newTransactionToPost).get(0);
        assertEquals(newTransactionToPost.getTransactionID(), testResult.getTransactionID());
        assertEquals(newTransactionToPost.getBuyerID(), testResult.getBuyerID());
        assertEquals(newTransactionToPost.getItemID(), testResult.getItemID());
        assertEquals(newTransactionToPost.getPrice(), testResult.getPrice());
        assertEquals(newTransactionToPost.getQuantity(), testResult.getQuantity());
        assertEquals(newTransactionToPost.getPostTime(), testResult.getPostTime());
        assertEquals(newTransactionToPost.getCloseTime(), testResult.getCloseTime());

    }
    @Test
    // test transactionService.updateTransaction()
    public void testUpdateTransaction() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalArgumentException{
        // Initialize transaction BEFORE update
        Integer transaction_id = 1;
        Integer seller_id = 1;
        LocalDateTime date = LocalDateTime.now();
        Integer item_id = 1;
        int price = 100;
        int quantity = 1;
        boolean open = true, accept = false;
        Transaction transactionToUpdate = new Transaction(
                transaction_id,
                seller_id,
                "buyer",
                item_id,
                price,
                date,
                date,
                quantity,
                open,
                accept,
                null
        );

        // initialize transaction AFTER update
        Transaction expectedResult = new Transaction(
                transaction_id,
                seller_id,
                "buyer",
                item_id,
                99,
                date,
                date,
                2,
                open,
                true,
                null
        );

        // Mock finding the Player through player_id
        Mockito.when(transactionRepo.findById(transactionToUpdate.getTransactionID())).thenReturn(Optional.of(expectedResult));
        // Mock updating/saving the database
        Mockito.when(transactionRepo.existsById(transaction_id)).thenReturn(true);
        Mockito.when(transactionRepo.save(expectedResult)).thenReturn(expectedResult);

        // assert that transaction get correctly updated by checking all fields
        // assert
        Transaction testResult = transactionService.updateTransaction(transactionToUpdate).get(0);
        assertEquals(expectedResult.getTransactionID(), testResult.getTransactionID());
        assertEquals(expectedResult.getBuyerID(), testResult.getBuyerID());
        assertEquals(expectedResult.getItemID(), testResult.getItemID());
        assertEquals(expectedResult.getPrice(), testResult.getPrice());
        assertEquals(expectedResult.getQuantity(), testResult.getQuantity());
        assertEquals(expectedResult.getPostTime(), testResult.getPostTime());
        assertEquals(expectedResult.getCloseTime(), testResult.getCloseTime());
    }
}
