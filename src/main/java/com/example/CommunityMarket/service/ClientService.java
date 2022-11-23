package com.example.CommunityMarket.service;

import com.example.CommunityMarket.repository.ClientRepository;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    final
    ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    //find by ID
    public List<Client> getByID(Integer clientID) throws ResourceNotFoundException {
        Optional<Client> result = clientRepo.findById(clientID);
        if (result.isPresent()) {
            Client clientResult = result.get();
            return List.of(clientResult);
        } else {
            throw new ResourceNotFoundException("Client not found by ID in DB.");
        }
    }
    //find by template
    public List<Client> getClientsByTemplate(Integer client_id,
                                             String email,
                                             String company_name,
                                             String client_name) {
        return clientRepo.findByTemplate(client_id, email, company_name, client_name);
    }

    //post client
    public List<Client> postClient(Client client) {
        Client result = clientRepo.save(client);
        return List.of(result);
    }

    // put operation
    public List<Client> updateClient(Client client) throws ResourceNotFoundException {
        if(clientRepo.existsById(client.getClientID())) {
            Client result = clientRepo.save(client);
            return List.of(result);
        } else {
            throw new ResourceNotFoundException("Client not found by ID in DB, cannot update.");
        }
    }
    // delete operation
    public void deleteClientById(Integer client_id) throws ResourceNotFoundException {
        if(clientRepo.existsById(client_id)) {
            clientRepo.deleteById(client_id);
        } else {
            throw new ResourceNotFoundException("Client not found in DB, cannot delete");
        }
    }

}
