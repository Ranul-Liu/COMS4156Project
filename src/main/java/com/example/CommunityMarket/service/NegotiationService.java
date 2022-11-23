package com.example.CommunityMarket.service;

import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.model.User;
import com.example.CommunityMarket.repository.NegotiationRepository;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Negotiation> getByID(Integer NegotiationID) {
        Optional<Negotiation> result = negotiationRepo.findById(NegotiationID);
        if (result.isPresent()) {
            Negotiation NegotiationResult = result.get();
            return List.of(NegotiationResult);
        }
        return Collections.emptyList();
    }

    public List<Negotiation> addNegotiation(Negotiation negotiation) {
        Integer fk_transaction_id = negotiation.getTransaction().getTransactionID();
        Optional<Transaction> result = transactionRepository.findById(fk_transaction_id);
        if (result.isPresent()) {
            Transaction transactionResult = result.get();
            negotiation.setTransaction(transactionResult);
            Negotiation NegotiationResult = negotiationRepo.save(negotiation);
            return List.of(NegotiationResult);
        } else {
            throw new IllegalArgumentException("Transaction not found by ID in DB.");
        }

    }

    public List<Negotiation> updateNegotiation(Negotiation negotiation) {
        if (getByID(negotiation.getNegotiation_id()).size() >= 1) {
            Negotiation result = negotiationRepo.save(negotiation);
            return List.of(result);
        } else {
            throw new IllegalArgumentException("Negotiation not found by ID in DB, cannot update");
        }
    }


    public List<Negotiation> getByTransactionID(Integer fk_transaction_id) {
        List<Negotiation> result = negotiationRepo.findByTransactionID(fk_transaction_id);
        if (result!=null) {
            return result;
        }
        return Collections.emptyList();
    }

    public Negotiation acceptNegotiation(Integer negotiation_id){
        //find the negotiation
        Optional<Negotiation> result = negotiationRepo.findById(negotiation_id);
        if(result.isPresent()){
            //accept and close the negotiation
            Negotiation negotiation= result.get();
            negotiation.setAccept(true);
            negotiation.setOpen(false);
            //accept and close transaction,set buyer_id
            Transaction transactionResult= negotiation.getTransaction();
            transactionResult.setAccept(true);
            transactionResult.setOpen(false);
            transactionResult.setBuyerID(negotiation.getBuyer_id());
            //save result in database
            transactionRepository.save(transactionResult);
            negotiationRepo.save(negotiation);
            return negotiation;
        } else {
            throw new IllegalArgumentException("Negotiation not found by ID in DB, cannot accept");
        }

    }
}