package com.example.CommunityMarket.service;

import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.repository.NegotiationRepository;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class NegotiationService {
    @Autowired
    NegotiationRepository negotiationRepo;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;

    public Negotiation getByID(Integer NegotiationID) throws ResourceNotFoundException {
        Optional<Negotiation> result = negotiationRepo.findById(NegotiationID);
        if (result.isPresent()) {
            Negotiation NegotiationResult = result.get();
            return NegotiationResult;
        } else {
            throw new ResourceNotFoundException("Negotiation not found by ID");
        }
    }

    public List<Negotiation> addNegotiation(Negotiation negotiation) throws ResourceNotFoundException {
        Integer fk_transaction_id = negotiation.getTransaction().getTransactionID();
        Optional<Transaction> result = transactionRepository.findById(fk_transaction_id);
        if (result.isPresent()) {
            //set negotiation post_time
            LocalDateTime time = LocalDateTime.now();
            negotiation.setPost_time(time);
            negotiation.setOpen(true);
            //set the transaction object by transaction_id
            Transaction transactionResult = result.get();
            negotiation.setTransaction(transactionResult);
            Negotiation NegotiationResult = negotiationRepo.save(negotiation);
            return List.of(NegotiationResult);
        } else {
            throw new ResourceNotFoundException("Transaction not found by ID in DB.");
        }

    }
    // no longer needed
    /*public List<Negotiation> updateNegotiation(Negotiation negotiation) throws ResourceNotFoundException {
        if (getByID(negotiation.getNegotiation_id()).size() >= 1) {
            Negotiation result = negotiationRepo.save(negotiation);
            return List.of(result);
        } else {
            throw new ResourceNotFoundException("Negotiation not found by ID in DB, cannot update");
        }
    }*/


    public List<Negotiation> getByTransactionID(Integer fk_transaction_id) throws ResourceNotFoundException {
        List<Negotiation> result = negotiationRepo.findByTransactionID(fk_transaction_id);
        if (result!=null) {
            return result;
        } else {
            throw new ResourceNotFoundException("Negotiation not found by transaction_id");
        }
    }

    public Negotiation acceptNegotiation(Integer negotiation_id) throws ResourceNotFoundException {
        //find the negotiation
        Optional<Negotiation> result = negotiationRepo.findById(negotiation_id);
        if(result.isPresent()){
            Negotiation negotiation= result.get();
            //set negotiation post_time
            negotiation.setClose_time(LocalDateTime.now());
            //accept and close the negotiation
            negotiation.setAccept(true);
            negotiation.setOpen(false);
            //accept and close transaction,set buyer_id, set close_time
            Transaction transactionResult= negotiation.getTransaction();
            transactionResult.setAccept(true);
            transactionResult.setOpen(false);
            transactionResult.setBuyerID(negotiation.getBuyer_id());
            transactionResult.setCloseTime(LocalDateTime.now());
            //save result in database
            transactionRepository.save(transactionResult);
            negotiationRepo.save(negotiation);
            return negotiation;
        } else {
            throw new ResourceNotFoundException("Negotiation not found by ID in DB, cannot accept");
        }


    }
}
