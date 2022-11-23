package com.example.CommunityMarket.controller;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.model.Transaction;
import com.example.CommunityMarket.service.NegotiationService;
import com.example.CommunityMarket.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NegotiationController {
    private final NegotiationService negotiationService;
    private final TransactionService transactionService;

    public NegotiationController(NegotiationService negotiationService, TransactionService transactionService) {
        this.negotiationService = negotiationService;
        this.transactionService = transactionService;
    }

    public ResponseEntity<?> getById(@RequestParam(value = "negotiation_id", required = true) Integer negotiation_id) {
        List<Negotiation> result = negotiationService.getByID(negotiation_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // view all negotiation associated with {transaction_id}
    @RequestMapping(value="/view-negotiation",method = RequestMethod.GET)
    public ResponseEntity<?> getByTransactionID(@RequestParam(value = "fk_transaction_id", required = true) Integer fk_transaction_id) {
        List<Negotiation> result = negotiationService.getByTransactionID(fk_transaction_id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    // add a new negotiation
    @RequestMapping(value = "/add-negotiation", method = RequestMethod.POST)
    public ResponseEntity<?> addnegotiation(@RequestBody Negotiation newnegotiation) {
        List<Negotiation> result = negotiationService.addNegotiation(newnegotiation);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    //accept a negotiation
    @RequestMapping(value = "/accept-negotiation/{negotiation_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> acceptNegotiation(@PathVariable Integer negotiation_id) {
        Negotiation result = negotiationService.acceptNegotiation(negotiation_id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}

