package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Client;
import com.example.CommunityMarket.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClientController {
    private final HttpServletRequest request;

    @Autowired
    ClientService clientService;

    //private static final Logger Log = LoggerFactory.getLogger(CommunityMarketController.class);
    @Autowired
    public ClientController(HttpServletRequest request) { this.request = request; }

    /*@RequestMapping(value = "/getClient", method = RequestMethod.GET)
    public ResponseEntity<?> getClient(@RequestParam(value = "google_id") String google_id) {
        Log.info("Requesting client info with google_id {}",google_id);
        try{
            List<Client> result = clientService.getClientByGoogleId(google_id);
            Log.info("GET client request successful");
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            Log.error("Exception on GET client",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<?> postClient(@RequestBody Client newClient) {
        List<Client> result = clientService.postClient(newClient);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/client", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@RequestBody Client client) throws ResourceNotFoundException {
        List<Client> result = clientService.updateClient(client);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
