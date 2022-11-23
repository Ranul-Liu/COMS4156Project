package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
public class TransactionController {
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){ this.transactionService = transactionService;}
    // get by transaction id
    //@RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam(value = "transaction_id", required = true) Integer transaction_id) {
        List<Transaction> result = transactionService.getByID(transaction_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // get by transaction template
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactionByTemplate(
            @RequestParam(value = "transaction_id", required = false) Integer transaction_id,
            @RequestParam(value = "buyer_id", required = false) String buyer_id,
            @RequestParam(value = "seller_id", required = false) String seller_id,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "open", required = false) Boolean open,
            @RequestParam(value = "post_time", required = false) LocalDateTime post_time,
            @RequestParam(value = "close_time", required = false) LocalDateTime close_time,
            @RequestParam(value = "price", required = false) Integer price,
            @RequestParam(value = "accept",required = false) Boolean accept)
    {
        // get results
        List<Transaction> result = transactionService.getTransactionByTemplate(transaction_id,buyer_id,seller_id,quantity,open,post_time,close_time,price,accept);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // post a new transaction
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<?> addtransaction(@RequestBody Transaction newtransaction) {
        List<Transaction> result = transactionService.addTransaction(newtransaction);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.PUT)
    public ResponseEntity<?> updatetransaction(@RequestBody Transaction newtransaction) {
        List<Transaction> result = transactionService.updateTransaction(newtransaction);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }





}
