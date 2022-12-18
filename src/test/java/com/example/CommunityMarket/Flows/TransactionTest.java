package com.example.CommunityMarket.Flows;


import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.repository.PlayerRepository;
import com.example.CommunityMarket.repository.TransactionRepository;
import com.example.CommunityMarket.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PlayerRepository playerRepo;
    @MockBean
    private TransactionRepository transactionRepo;

    public TransactionTest() {
    }

    // test transactionService.getByID() exist
    @Test
    public void testGetTransactionByIdExist() throws ResourceNotFoundException {
        Integer transaction_id = 1;
        Transaction expectedResult = new Transaction(
                1,
                1,
                null,
                1,
                5,
                null,
                null,
                5,
                true,
                false
        );

        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.of(expectedResult));
        // assert
        Transaction testResult = transactionService.getByID(transaction_id).get(0);
        assertEquals(testResult, expectedResult);
    }

    @Test
    public void testGetTransactionByIDNotExist() throws ResourceNotFoundException {
        Integer transaction_id = 1;
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getByID(transaction_id),
                "Transaction is not found by ID");
    }

    // test transactionService.addTransaction()
    // Post method request body: item_id, initial_price, quantity
    @Test
    public void testAddTransactionSucceed() throws ResourceException, ResourceNotFoundException {
        Integer seller_id = 1;
        // initialize transaction request body
        Transaction transactionToPost = new Transaction(
                1,
                5,
                5
        );
        // Expected output
        Transaction expectedResult = new Transaction(
                1,
                1,
                null,
                1,
                5,
                LocalDateTime.now(),
                null,
                5,
                true,
                false
        );
        // mock saving in DB
        Mockito.when(transactionRepo.save(transactionToPost)).thenReturn(transactionToPost);
        // assert everything as expected but transaction_id because of auto-generation
        Transaction testResult = transactionService.addTransaction(transactionToPost, seller_id).get(0);
        assertEquals(expectedResult.getSellerID(), testResult.getSellerID());
        assertEquals(expectedResult.getBuyerID(), testResult.getBuyerID());
        assertEquals(expectedResult.getItemID(), testResult.getItemID());
        assertEquals(expectedResult.getQuantity(), testResult.getQuantity());
        assertEquals(expectedResult.getPrice(), testResult.getPrice());
        assertEquals(expectedResult.getPostTime().truncatedTo(ChronoUnit.MINUTES), testResult.getPostTime().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(expectedResult.getCloseTime(), testResult.getCloseTime());
        assertEquals(expectedResult.isOpen(), testResult.isOpen());
        assertEquals(expectedResult.isAccept(), testResult.isAccept());
    }

    @Test
    // test transactionService.updateTransaction()
    // Update method request body: item_id, initial_price, quantity
    public void testUpdateTransaction() throws ResourceException, ResourceNotFoundException {
        Integer transaction_id = 1;
        // transaction before update
        Transaction transactionBeforeUpdate = new Transaction(
                1,
                1,
                null,
                1,
                5,
                LocalDateTime.now(),
                null,
                5,
                true,
                false
        );
        // initialize update request body
        Transaction transactionForUpdate = new Transaction(
                10,
                123,
                456);
        // expected result after update
        Transaction expectedResult = new Transaction(
                1,
                1,
                null,
                10,
                123,
                LocalDateTime.now(),
                null,
                456,
                true,
                false
        );

        // Mock finding the  through player_id
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.of(transactionBeforeUpdate));
        // Mock updating/saving the database
        Mockito.when(transactionRepo.save(transactionBeforeUpdate)).thenReturn(transactionBeforeUpdate);
        // assert that transaction get correctly updated by checking all fields
        Transaction testResult = transactionService.updateTransaction(transactionForUpdate, transaction_id).get(0);
        assertEquals(expectedResult.getSellerID(), testResult.getSellerID());
        assertEquals(expectedResult.getBuyerID(), testResult.getBuyerID());
        assertEquals(expectedResult.getItemID(), testResult.getItemID());
        assertEquals(expectedResult.getQuantity(), testResult.getQuantity());
        assertEquals(expectedResult.getPrice(), testResult.getPrice());
        assertEquals(expectedResult.getPostTime().truncatedTo(ChronoUnit.MINUTES), testResult.getPostTime().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(expectedResult.getCloseTime(), testResult.getCloseTime());
        assertEquals(expectedResult.isOpen(), testResult.isOpen());
        assertEquals(expectedResult.isAccept(), testResult.isAccept());
    }

    @Test
    public void testUpdateTransactionNotExist() {
        Integer transaction_id = 1;
        Transaction transaction = new Transaction(
                10,
                1,
                null,
                1,
                5,
                LocalDateTime.now(),
                null,
                5,
                true,
                false
        );
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> transactionService.updateTransaction(transaction, transaction_id),
                "Transaction not found by ID in DB, cannot update");
    }

    @Test
    public void testCloseTransaction() throws ResourceNotFoundException {
        Integer transaction_id = 1;
        Transaction transactionBeforeClose = new Transaction(
                1,
                1,
                null,
                1,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                5,
                true,
                false
        );
        // expected result after update
        Transaction expectedResult = new Transaction(
                1,
                1,
                null,
                1,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                5,
                false,
                false
        );

        // Mock finding the  through player_id
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.of(transactionBeforeClose));
        // Mock updating/saving the database
        Mockito.when(transactionRepo.save(transactionBeforeClose)).thenReturn(transactionBeforeClose);
        // assert that transaction get correctly updated by checking all fields
        Transaction testResult = transactionService.closeTransaction(transaction_id).get(0);
        assertEquals(expectedResult.getSellerID(), testResult.getSellerID());
        assertEquals(expectedResult.getBuyerID(), testResult.getBuyerID());
        assertEquals(expectedResult.getItemID(), testResult.getItemID());
        assertEquals(expectedResult.getQuantity(), testResult.getQuantity());
        assertEquals(expectedResult.getPrice(), testResult.getPrice());
        assertEquals(expectedResult.getPostTime().truncatedTo(ChronoUnit.MINUTES), testResult.getPostTime().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(expectedResult.getCloseTime().truncatedTo(ChronoUnit.MINUTES), testResult.getCloseTime().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(expectedResult.isOpen(), testResult.isOpen());
        assertEquals(expectedResult.isAccept(), testResult.isAccept());

    }

    @Test
    public void testCloseTransactionNotExist() {
        Integer transaction_id = 1;
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> transactionService.closeTransaction(transaction_id),
                "Transaction not found by ID in DB, cannot close");
    }
}
