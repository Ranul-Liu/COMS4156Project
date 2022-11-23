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

    // add a new negotiation
    @RequestMapping(value = "/addnegotiation", method = RequestMethod.POST)
    public ResponseEntity<?> addnegotiation(@RequestBody Negotiation newnegotiation) {
        List<Negotiation> result = negotiationService.addNegotiation(newnegotiation);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}

