package com.example.CommunityMarket.controller;
import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Negotiation;
import com.example.CommunityMarket.service.NegotiationService;
import com.example.CommunityMarket.service.PlayerService;
import com.example.CommunityMarket.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NegotiationController {
    private final NegotiationService negotiationService;
    private final TransactionService transactionService;

    private final PlayerService playerService;

    public NegotiationController(NegotiationService negotiationService, TransactionService transactionService, PlayerService playerService) {
        this.negotiationService = negotiationService;
        this.transactionService = transactionService;
        this.playerService = playerService;
    }

    public ResponseEntity<?> getById(@RequestParam(value = "negotiation_id", required = true) Integer negotiation_id) throws ResourceNotFoundException {
        Negotiation result = negotiationService.getByID(negotiation_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // view all negotiation associated with {transaction_id}
    @RequestMapping(value="/view-negotiation",method = RequestMethod.GET)
    public ResponseEntity<?> getByTransactionID(@RequestParam(value = "fk_transaction_id", required = true) Integer fk_transaction_id) throws ResourceNotFoundException {
        List<Negotiation> result = negotiationService.getByTransactionID(fk_transaction_id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    // add a new negotiation
    @RequestMapping(value = "/add-negotiation/{buyer_id}", method = RequestMethod.POST)
    public ResponseEntity<?> addnegotiation(@RequestBody Negotiation newnegotiation,
                                            @PathVariable("buyer_id") Integer buyer_id)
            throws ResourceException, ResourceNotFoundException {
        try {
            playerService.checkPlayerLoggedInById(buyer_id);
        }
        catch(Exception e) {
            throw e;
        }
        List<Negotiation> result = negotiationService.addNegotiation(newnegotiation);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    //accept a negotiation
    @RequestMapping(value = "/accept-negotiation/{seller_id}/{negotiation_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> acceptNegotiation(@PathVariable Integer negotiation_id,
                                               @PathVariable Integer seller_id)
            throws ResourceException, ResourceNotFoundException {
        try {
            playerService.checkPlayerLoggedInById(seller_id);
        }
        catch(Exception e) {
            throw e;
        }
        Negotiation result = negotiationService.acceptNegotiation(negotiation_id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}

