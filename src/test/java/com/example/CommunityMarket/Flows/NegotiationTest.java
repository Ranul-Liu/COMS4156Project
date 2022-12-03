
package com.example.CommunityMarket.Flows;

import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.repository.NegotiationRepository;
import com.example.CommunityMarket.repository.TransactionRepository;
import com.example.CommunityMarket.service.NegotiationService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NegotiationTest {
    @Autowired
    private NegotiationService negotiationService;

    @MockBean
    private NegotiationRepository negotiationRepo;
    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void testCheckGetByIdExistsForNegotiation() throws ResourceNotFoundException {
        Integer negotiation_id = 1;
        Transaction transaction = new Transaction(
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
        Negotiation negotiationToFind = new Negotiation(
                1,
                111,
                null,
                null,
                5,
                5,
                true,
                false,
                transaction
        );
        Negotiation expectedResult = new Negotiation(
                1,
                111,
                null,
                null,
                5,
                5,
                true,
                false,
                transaction
        );
        Mockito.when(negotiationRepo.findById(negotiation_id)).thenReturn(Optional.of(negotiationToFind));
        Negotiation actualResult = negotiationService.getByID(negotiation_id);
        assertEquals(expectedResult.getNegotiation_id(),actualResult.getNegotiation_id());
        assertEquals(expectedResult.getPrice(), actualResult.getPrice());
        assertEquals(expectedResult.getQuantity(), actualResult.getQuantity());
        assertEquals(expectedResult.getBuyer_id(), actualResult.getBuyer_id());
        assertEquals(expectedResult.getTransaction(), actualResult.getTransaction());
    }

    @Test
    public void testGetByIDEmptyForNegotiation() throws ResourceNotFoundException {
        Integer negotiation_id = 1;
        Mockito.when(negotiationRepo.findById(negotiation_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->negotiationService.getByID(negotiation_id),
                "Negotiation not found by ID");
    }

    @Test
    public void testAddNegotiationExists() throws ResourceNotFoundException {
        //given
        Transaction transaction = new Transaction(
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
        // add negotiation request body
        // input: buyer_id,
        //        price,
        //        quantity,
        //        "transaction":{
        //              "transaction_id":1
        //         }
        Negotiation negotiationToAdd = new Negotiation(
                2,
                3,
                5,
                transaction
        );
        // should get
        Negotiation expectedResult = new Negotiation(
                1,
                2,
                LocalDateTime.now(),
                null,
                3,
                5,
                true,
                false,
                transaction
        );
        Mockito.when(transactionRepository.findById(negotiationToAdd.getTransaction().getTransactionID())).thenReturn(Optional.of(transaction));
        Mockito.when(negotiationRepo.save(negotiationToAdd)).thenReturn(negotiationToAdd);
        List<Negotiation> result = negotiationService.addNegotiation(negotiationToAdd);
        //assert everything except time and auto-generated negotiation_id
        assertEquals(expectedResult.getPrice(),result.get(0).getPrice());
        assertEquals(expectedResult.getQuantity(),result.get(0).getQuantity());
        assertEquals(expectedResult.getTransaction(),result.get(0).getTransaction());
        assertEquals(expectedResult.isAccept(),result.get(0).isAccept());
        assertEquals(expectedResult.isOpen(),result.get(0).isOpen());

    }

    @Test
    public void testAddNegotiationNotExists() {
        Integer transaction_id = 1;
        Transaction transaction = new Transaction(10,1,1);
        Negotiation negotiationToAdd = new Negotiation(
                2,
                3,
                5,
                transaction
        );
        Mockito.when(transactionRepository.findById(transaction_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->negotiationService.addNegotiation(negotiationToAdd),
                "Transaction not found by ID in DB.");
    }

    /*@Test
    public void testUpdateNegotiation() {

        //given
        Transaction transaction = new Transaction(
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
        // update negotiation request body
        // input: buyer_id,
        //        price,
        //        quantity,
        //        "transaction":{
        //              "transaction_id":1
        //         }
        Negotiation negotiationForUpdate = new Negotiation(
                2,
                3,
                5,
                transaction
        );
        // should get
        Negotiation expectedResult = new Negotiation(
                1,
                2,
                LocalDateTime.now(),
                null,
                3,
                5,
                true,
                false,
                transaction
        );

        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(optNegotiation);
        Mockito.when(negotiationRepo.save(any(Negotiation.class))).thenReturn(newNegotiation);
        assertEquals(List.of(optNegotiation.get()), negotiationService.updateNegotiation(newNegotiation));
    }

    @Test
    public void testExceptionUpdateNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(Optional.empty());
        negotiationService.updateNegotiation(newNegotiation);
    }*/



    @Test
    public void testAcceptNegotiation() throws ResourceNotFoundException {
        Integer negotiation_id = 1;
        Integer transaction_id = 1;
        Transaction transaction = new Transaction(
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
        Transaction transaction_result = new Transaction(
                1,
                1,
                111,
                1,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                5,
                false,
                true
        );
        Negotiation negotiationToAccept = new Negotiation(
                1,
                111,
                LocalDateTime.now(),
                null,
                5,
                5,
                true,
                false,
                transaction
        );
        Negotiation expectedResult = new Negotiation(
                1,
                111,
                LocalDateTime.now(),
                null,
                5,
                5,
                false,
                true,
                transaction_result
        );
        Mockito.when(negotiationRepo.findById(negotiation_id)).thenReturn(Optional.of(negotiationToAccept));
        Mockito.when(negotiationRepo.save(negotiationToAccept)).thenReturn(negotiationToAccept);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction_result);
        //assert all but post and close time
        assertEquals(expectedResult.getNegotiation_id(), negotiationService.acceptNegotiation(negotiation_id).getNegotiation_id());
        assertEquals(expectedResult.getPrice(), negotiationService.acceptNegotiation(negotiation_id).getPrice());
        assertEquals(expectedResult.getQuantity(), negotiationService.acceptNegotiation(negotiation_id).getQuantity());
        assertEquals(expectedResult.isOpen(), negotiationService.acceptNegotiation(negotiation_id).isOpen());
        assertEquals(expectedResult.isAccept(), negotiationService.acceptNegotiation(negotiation_id).isAccept());
        assertEquals(expectedResult.getBuyer_id(), negotiationService.acceptNegotiation(negotiation_id).getBuyer_id());
        //assert all in transaction but post and close time
        assertEquals(expectedResult.getTransaction().getTransactionID(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getTransactionID());
        assertEquals(expectedResult.getTransaction().getBuyerID(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getBuyerID());
        assertEquals(expectedResult.getTransaction().getSellerID(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getSellerID());
        assertEquals(expectedResult.getTransaction().getItemID(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getItemID());
        assertEquals(expectedResult.getTransaction().getPrice(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getPrice());
        assertEquals(expectedResult.getTransaction().getQuantity(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().getQuantity());
        assertEquals(expectedResult.getTransaction().isAccept(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().isAccept());
        assertEquals(expectedResult.getTransaction().isOpen(), negotiationService.acceptNegotiation(negotiation_id).getTransaction().isOpen());
    }
    @Test
    public void testExceptionAcceptNegotiation() {
        Integer negotiation_id = 1;
        Mockito.when(negotiationRepo.findById(negotiation_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->negotiationService.acceptNegotiation(negotiation_id),
                "Negotiation not found by ID in DB, cannot accept");
    }
}
