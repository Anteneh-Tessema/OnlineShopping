package com.cs490.onlineshopping.admin.service;

import com.cs490.onlineshopping.admin.model.Client;
import com.cs490.onlineshopping.admin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
}