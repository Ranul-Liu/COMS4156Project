package com.example.CommunityMarket.service;

import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.PlayerRepository;
import com.example.CommunityMarket.repository.TransactionRepository;
import com.example.CommunityMarket.model.Transaction;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionService {

    @Autowired

    TransactionRepository transactionRepo;
    PlayerRepository playerRepo;

    public List<Transaction> getByID(Integer transactionID) throws ResourceNotFoundException {
        Optional<Transaction> result = transactionRepo.findById(transactionID);
        if (result.isPresent()) {
            Transaction transactionResult = result.get();
            return List.of(transactionResult);
        } else {
            throw new ResourceNotFoundException("Transaction is not found by ID");
        }

    }
    public List<Transaction> getTransactionByTemplate(Integer transaction_id,
                                                      Integer buyer_id,
                                                      Integer seller_id,
                                                      Integer quantity,
                                                      Boolean is_open,
                                                      LocalDateTime post_time,
                                                      LocalDateTime close_time,
                                                      Integer price,
                                                      Boolean is_accept){
        return transactionRepo.findByTemplate(transaction_id,buyer_id,seller_id,quantity,is_open,post_time,close_time,price,is_accept);
    }

    public List<Transaction> addTransaction(Transaction transaction,Integer seller_id) throws ResourceException {
        //todo: add checkPostTransactionInput, throw exception if invalid
        LocalDateTime time = LocalDateTime.now();
        transaction.setPostTime(time);
        transaction.setOpen(true);
        transaction.setSellerID(seller_id);
        Transaction result = transactionRepo.save(transaction);
        return List.of(result);

    }
    public List<Transaction> updateTransaction(Transaction newtransaction, Integer transaction_id) throws IllegalArgumentException, ResourceNotFoundException {
        Optional<Transaction> Result = transactionRepo.findById(transaction_id);
        if (Result.isPresent()) {
            Transaction transaction = Result.get();
            //seller can only update price, quantity
            transaction.setPrice(newtransaction.getPrice());
            //todo: check new quantity < current quantity
            transaction.setItemID(newtransaction.getItemID());
            transaction.setTransactionID(newtransaction.getItemID());
            transaction.setQuantity(newtransaction.getQuantity());
            transactionRepo.save(transaction);
            return List.of(transaction);
        } else {
            throw new ResourceNotFoundException("Transaction not found by ID in DB, cannot update");
        }
    }

    public List<Transaction> closeTransaction(Integer transaction_id) throws ResourceNotFoundException {
        Optional<Transaction> Result = transactionRepo.findById(transaction_id);
        if (Result.isPresent()) {
            Transaction transaction = Result.get();
            transaction.setOpen(false);
            LocalDateTime current_time = LocalDateTime.now();
            transaction.setCloseTime(current_time);
            transactionRepo.save(transaction);
            return List.of(transaction);
        } else {
            throw new ResourceNotFoundException("Transaction not found by ID in DB, cannot close");
        }
    }


}
