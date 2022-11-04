package com.example.CommunityMarket.controller;

import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Client;
import com.example.CommunityMarket.service.ClientService;
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

    @Autowired
    public ClientController(HttpServletRequest request) { this.request = request; }

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
