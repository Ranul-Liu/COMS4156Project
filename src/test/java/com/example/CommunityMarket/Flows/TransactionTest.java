package com.example.CommunityMarket.Flows;


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

    /*@Test
    public void testCheckGetByIdExistsForTransaction() {

        Integer transaction_id = 1;
        Transaction newTransaction = new Transaction(
                1,
                "user1",
                "user2",
                1,
                5,
                5
        );

        Optional<Transaction> optTransaction = Optional.of(newTransaction);
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(optTransaction);
        assertEquals(List.of(optTransaction.get()), transactionService.getByID(transaction_id));
    }*/

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetByIDExceptForTransaction() {
        Integer transaction_id = 1;
        Mockito.when(transactionRepo.findById(transaction_id)).thenReturn(Optional.empty());
        transactionService.getByID(transaction_id);
    }*/
    /*@Test
    public void testPostTransaction() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Initialize transaction before post method called
        Transaction beforeTransaction = new Transaction(null,
                "user1",
                "user2",
                1,
                5,
                5
        );
        Transaction afterTransaction = new Transaction(1,
                "user1",
                "user2",
                1,
                5,
                5
        );
        // save the transaction
        Mockito.when(transactionRepo.save(beforeTransaction)).thenReturn(afterTransaction);
        // assert that transaction get correctly updated with post method
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getTransactionID(), 1);
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getSellerID(), "user1");
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getBuyerID(), "user2");
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getItemID(), 1);
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getPrice(), 5);
        assertEquals(transactionService.addTransaction(beforeTransaction).get(0).getQuantity(), 5);

    }
    @Test
    // Test the transaction get correctly updated
    public void testUpdateTransaction() throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalArgumentException{
        // Initialize transaction before update method called
        Transaction beforeTransaction = new Transaction(1,
                "user1",
                "user2",
                1,
                5,
                5
        );
        Transaction afterTransaction = new Transaction(1,
                "newuser1",
                "newuser2",
                2,
                10,
                10
        );
        // check if transaction exists
        Mockito.when(transactionRepo.findById(afterTransaction.getTransactionID())).thenReturn(Optional.of(afterTransaction));
        // save the changes
        Mockito.when(transactionRepo.save(afterTransaction)).thenReturn(afterTransaction);
        // assert that transaction get correctly updated by checking all fields
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getTransactionID(), 1);
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getSellerID(), "newuser1");
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getBuyerID(), "newuser2");
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getItemID(), 2);
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getPrice(), 10);
        assertEquals(transactionService.updateTransaction(afterTransaction).get(0).getQuantity(), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdateTransaction() throws ClassNotFoundException, InstantiationException, IllegalArgumentException {
        //Instantiate updated transacton
        Transaction afterTransaction = new Transaction(1,
                "newuser1",
                "newuser2",
                2,
                10,
                10
        );
        //transaction id dos not exist
        Mockito.when(transactionRepo.existsById((afterTransaction.getTransactionID()))).thenReturn(false);
        //call update method
        transactionService.updateTransaction(afterTransaction);
    }


*/

}
