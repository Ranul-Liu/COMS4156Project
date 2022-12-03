package com.example.CommunityMarket.Flows;

import com.example.CommunityMarket.exceptions.ResourceException;
import com.example.CommunityMarket.exceptions.ResourceNotFoundException;
import com.example.CommunityMarket.model.Client;
import com.example.CommunityMarket.model.Player;
import com.example.CommunityMarket.repository.*;
import com.example.CommunityMarket.service.ClientService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {

    @Autowired
    ClientService clientService;

    @MockBean
    ClientRepository clientRepo;


    @Test
    public void testCheckGetByIdExists() throws ResourceNotFoundException {

        Integer client_id = 1;
        Client newClient = new Client(1,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");
        Optional<Client> optClient = Optional.of(newClient);
        Mockito.when(clientRepo.findById(client_id)).thenReturn(optClient);

        Client result = clientService.getByID(client_id).get(0);

        assertEquals(client_id, result.getClientID());
    }

    @Test
    public void testCheckGetByIdNotExists() {
        Integer client_id = 1;
        Optional<Client> optClient = Optional.empty();
        Mockito.when(clientRepo.findById(client_id)).thenReturn(optClient);
        assertThrows(ResourceNotFoundException.class,
                ()->clientService.getByID(client_id));

    }

    @Test
    public void testGetClientsByTemplate() {
        Client client = new Client(1,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");

        Mockito.when(clientRepo.findByTemplate(null,
                null,
                null,
                "client1@gmail.com")).thenReturn(List.of(client));
        Client result = clientService.getClientsByTemplate(null,
                "client1@gmail.com",
                null,
                null).get(0);
        assertEquals(result.getClientID(), client.getClientID());
    }

    @Test
    public void testPostClient() {
        Client client1 = new Client(null,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");


        Client client2 = new Client(136,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");


        Mockito.when(clientRepo.save(client1)).thenReturn(client2);

        Assertions.assertNotEquals(client1.getClientID(), client2.getClientID());
        assertEquals(client1.getGoogle_id(), client2.getGoogle_id());
        assertEquals(client1.getCompanyName(), client2.getCompanyName());
    }

    @Test
    public void testUpdateClient() throws ResourceNotFoundException {

        Client client = new Client(13,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");

        Mockito.when(clientRepo.existsById(13)).thenReturn(Boolean.TRUE);
        Mockito.when(clientRepo.save(client)).thenReturn(client);

        Client result = clientService.updateClient(client).get(0);
        assertEquals(client.getClientID(), result.getClientID());
        assertEquals(client.getGoogle_id(), result.getGoogle_id());

    }

    @Test
    public void testUpdateClientNotFound() {
        Integer update_client_id = 13;
        Client client = new Client(13,
                "client1@gmail.com",
                "client1",
                "Company1",
                "4");
        Mockito.when(clientRepo.findById(update_client_id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->clientService.updateClient(client));
    }

    @Test
    public void testDeleteClientById() throws ResourceNotFoundException {

        Integer client_id = 10;

        Mockito.when(clientRepo.existsById(client_id)).thenReturn(Boolean.TRUE);
        clientService.deleteClientById(client_id);

    }

    @Test
    public void testDeleteClientByIdNotFound() {
        Integer client_id = 10;

        Mockito.when(clientRepo.findById(client_id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                ()->clientService.deleteClientById(client_id));

    }

}
