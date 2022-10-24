package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.Item;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    // get by transaction id
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam(value = "transaction_id", required = false) Integer transaction_id) {
        List<Transaction> result = transactionService.getByID(transaction_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // post 
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<?> addtransaction(@RequestBody Transaction newtransaction) {
        List<Transaction> result = transactionService.addTransaction(newtransaction);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /*@RequestMapping(value = "/transaction", method = RequestMethod.PUT)
    public ResponseEntity<?> updatetransaction(@RequestBody Transaction newtransaction) {
        List<Transaction> result = transactionService.updateTransaction(newtransaction);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/
}
