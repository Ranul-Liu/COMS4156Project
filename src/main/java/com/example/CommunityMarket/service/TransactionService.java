package com.example.CommunityMarket.service;

import com.example.CommunityMarket.Repository.ItemRepository;
import com.example.CommunityMarket.Repository.TransactionRepository;
import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionService {

    @Autowired

    TransactionRepository transactionRepo;

    public List<Transaction> getByID(Integer transactionID) {
        Optional<Transaction> result = transactionRepo.findById(transactionID);
        if (result.isPresent()) {
            Transaction transactionResult = result.get();
            return List.of(transactionResult);
        }
        return Collections.emptyList();
    }

    public List<Transaction> addTransaction(Transaction transaction) {

        Transaction result = transactionRepo.save(transaction);
        return List.of(result);
    }
    public List<Transaction> updateTransaction(Transaction transaction) throws IllegalArgumentException {
        if (getByID(transaction.getTransactionID()).size() >= 1) {
            Transaction result = transactionRepo.save(transaction);
            return List.of(result);
        } else {
            throw new IllegalArgumentException("Transaction not found by ID in DB, cannot update");
        }
    }


}
