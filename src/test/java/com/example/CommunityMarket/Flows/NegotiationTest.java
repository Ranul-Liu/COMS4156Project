package com.example.CommunityMarket.Flows;

import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.repository.NegotiationRepository;
import com.example.CommunityMarket.service.NegotiationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testCheckGetByIdExistsForNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Optional<Negotiation> optNegotiation = Optional.of(newNegotiation);
        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(optNegotiation);
        assertEquals(List.of(optNegotiation.get()), negotiationService.getByID(negotiation_id));
    }

    @Test
    public void testGetByIDEmptyForNegotiation() {
        Integer negotiation_id = 1;
        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(Optional.empty());
        assertEquals(Collections.emptyList(), negotiationService.getByID(negotiation_id))
    }

    @Test
    public void testAddNegotiationExists() {
        Integer id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Optional<Negotiation> optNegotiation = Optional.of(newNegotiation);

        Mockito.when(transactionRepository.findById(id.toString())).thenReturn(Optional.of(newTransaction));
        Mockito.when(negotiationRepo.save(any(Negotiation.class))).thenReturn(newNegotiation);
        assertEquals(List.of(optNegotiation.get()), negotiationService.addNegotiation(newNegotiation));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNegotiationNotExists() {
        Integer id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Mockito.when(transactionRepository.findById(id.toString())).thenReturn(Optional.empty());
        negotiationService.addNegotiation(newNegotiation);
    }

    @Test
    public void testUpdateNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Optional<Negotiation> optNegotiation = Optional.of(newNegotiation);

        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(optNegotiation);
        Mockito.when(negotiationRepo.save(any(Negotiation.class))).thenReturn(newNegotiation);
        assertEquals(List.of(optNegotiation.get()), negotiationService.updateNegotiation(newNegotiation));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionUpdateNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(Optional.empty());
        negotiationService.updateNegotiation(newNegotiation);
    }

    @Test
    public void testGetByTransactionIDExists() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Optional<Negotiation> optNegotiation = Optional.of(newNegotiation);
        Mockito.when(negotiationRepo.findByTransactionID(negotiation_id.toString())).thenReturn(optNegotiation);
        assertEquals(List.of(optNegotiation.get()), negotiationService.getByTransactionID(negotiation_id));
    }

    @Test
    public void testGetByTransactionIDNotExists() {
        Integer negotiation_id = 1;
        Mockito.when(negotiationRepo.findByTransactionID(negotiation_id.toString())).thenReturn(Optional.empty());
        assertEquals(Collections.emptyList(), negotiationService.getByTransactionID(negotiation_id))
    }

    @Test
    public void testAcceptNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);

        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(Optional.of(newNegotiation));
        Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(newTransaction);
        Mockito.when(negotiationRepo.save(any(Negotiation.class))).thenReturn(newNegotiation);
        assertEquals(newNegotiation, negotiationService.acceptNegotiation(negotiation_id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAcceptNegotiation() {
        Integer negotiation_id = 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Transaction newTransaction = new Transaction(1, "seller", "buyer", 1, 1, localDateTime, null, 1, true, false, null);
        Negotiation newNegotiation = new Negotiation(1, "buyer", localDateTime, null, 1, 1, true, false, newTransaction);
        Mockito.when(negotiationRepo.findById(negotiation_id.toString())).thenReturn(Optional.empty());
        negotiationService.acceptNegotiation(negotiation_id);
    }
}