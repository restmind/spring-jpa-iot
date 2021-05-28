package com.shuplat.spring.service;

import com.shuplat.spring.domain.Client;
import com.shuplat.spring.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements AbstractService<Client, Integer> {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.getOne(id);
    }

    @Override
    public Client create(Client newObject) {
        return clientRepository.save(newObject);
    }

    @Override
    public Client update(Integer integer, Client object) {
        if (clientRepository.findById(integer).isPresent()) {
            object.setId(integer);
            return clientRepository.save(object);
        } else {
            return null;
        }

    }

    @Override
    public void deleteById(Integer id) {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
        }
    }
}
