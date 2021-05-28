package com.shuplat.spring.controller;

import com.shuplat.spring.DTO.ClientDTO;
import com.shuplat.spring.domain.Client;
import com.shuplat.spring.domain.Orders;
import com.shuplat.spring.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping(value = "/client")
@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<Client> clients = clientService.getAll();
        List<ClientDTO> clientDtos = new ArrayList<>();
        for (Client client : clients) {
            Set<Integer> ordersId = new HashSet<>();
            for(Orders order: client.getOrders()) {
                ordersId.add(order.getId());
            }
            ClientDTO clientDTO = new ClientDTO (
                    client.getId(),
                    client.getName(),
                    client.getSurname(),
                    client.getEmail(),
                    ordersId
            );
            clientDtos.add(clientDTO);
        }
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Integer id) {
        Client client = clientService.getById(id);
        Set<Integer> ordersId = new HashSet<>();
        for(Orders order: client.getOrders()) {
            ordersId.add(order.getId());
        }
        ClientDTO clientDTO = new ClientDTO (
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                ordersId
        );
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Client newClient) {
        clientService.create(newClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id,
                                                 @RequestBody Client client) {
        Client oldClient = clientService.getById(id);
        if (oldClient != null) {
            clientService.update(id, client);
            ClientDTO oldClientDTO = new ClientDTO (
                    client.getId(),
                    client.getName(),
                    client.getSurname(),
                    client.getEmail()
            );
            return new ResponseEntity<>(oldClientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (clientService.getById(id) != null) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
