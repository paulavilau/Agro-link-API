package com.myproject.agrolink.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.agrolink.dao.ClientRepository;
import com.myproject.agrolink.dao.CountyRepository;
import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.County;
import com.myproject.agrolink.requestmodel.AddClientRequest;
import com.myproject.agrolink.requestmodel.ModifyClientRequest;

@Service
@Transactional
public class ClientService {

    private ClientRepository clientRepository;

    private CountyRepository countyRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, CountyRepository countyRepository) {
        this.clientRepository = clientRepository;
        this.countyRepository = countyRepository;
    }

    public Client findClientById(Integer clientId) throws Exception {
        Optional<Client> client = clientRepository.findById(clientId);

        if (!client.isPresent()) {
            throw new Exception("Client doesn t exist");
        }

        return client.get();
    }

    public Client addClient(AddClientRequest clientRequest) throws Exception {
        Client validateClient = clientRepository.findByFirebaseId(clientRequest.getFirebaseId());

        if (validateClient != null) {
            throw new Exception("Client already registered");
        }

        Client client = new Client();

        // firebaseId
        client.setFirebaseId(clientRequest.getFirebaseId());

        // firstName
        if (clientRequest.getFirstName() != null && clientRequest.getFirstName().isPresent()) {
            client.setFirstName(clientRequest.getFirstName().map(
                    Object::toString).orElse(null));
        }

        // lastName
        if (clientRequest.getLastName() != null && clientRequest.getLastName().isPresent()) {
            client.setLastName(clientRequest.getLastName().map(
                    Object::toString).orElse(null));
        }

        // email
        client.setEmail(clientRequest.getEmail());

        // phone
        if (clientRequest.getPhone() != null && clientRequest.getPhone().isPresent()) {
            client.setPhone(clientRequest.getPhone().map(
                    Object::toString).orElse(null));
        }

        // companyName
        if (clientRequest.getCompanyName() != null && clientRequest.getCompanyName().isPresent()) {
            client.setCompanyName(clientRequest.getCompanyName().map(
                    Object::toString).orElse(null));
        }

        // cif
        if (clientRequest.getCif() != null && clientRequest.getCif().isPresent()) {
            client.setCif(clientRequest.getCif().map(
                    Object::toString).orElse(null));
        }

        // county
        Optional<County> county = countyRepository.findById(clientRequest.getCountyId());

        if (!county.isPresent()) {
            throw new Exception("Invalid county id");
        }

        client.setCounty(county.get());

        // creationDate
        client.setCreationDt(LocalDateTime.now().toString());

        // active
        client.setActive(1);

        clientRepository.save(client);
        return client;
    }

    public Client modifyClient(ModifyClientRequest clientRequest) throws Exception {
        Client client = findClientById(clientRequest.getId());

        // firstName
        if (clientRequest.getFirstName() != null && clientRequest.getFirstName().isPresent()) {
            client.setFirstName(clientRequest.getFirstName().map(
                    Object::toString).orElse(null));
        }

        // lastName
        if (clientRequest.getLastName() != null && clientRequest.getLastName().isPresent()) {
            client.setLastName(clientRequest.getLastName().map(
                    Object::toString).orElse(null));
        }

        // email
        if (clientRequest.getEmail() != null && clientRequest.getEmail().isPresent()) {
            client.setEmail(clientRequest.getEmail().map(
                    Object::toString).orElse(null));
        }

        // phone
        if (clientRequest.getPhone() != null && clientRequest.getPhone().isPresent()) {
            client.setPhone(clientRequest.getPhone().map(
                    Object::toString).orElse(null));
        }

        // companyName
        if (clientRequest.getCompanyName() != null && clientRequest.getCompanyName().isPresent()) {
            client.setCompanyName(clientRequest.getCompanyName().map(
                    Object::toString).orElse(null));
        }

        // cif
        if (clientRequest.getCif() != null && clientRequest.getCif().isPresent()) {
            client.setCif(clientRequest.getCif().map(
                    Object::toString).orElse(null));
        }

        // address
        if (clientRequest.getAddress() != null && clientRequest.getAddress().isPresent()) {
            client.setAddress(clientRequest.getAddress().map(
                    Object::toString).orElse(null));

        }

        // active
         if (clientRequest.getActive() != null && clientRequest.getActive().isPresent()) {
            client.setActive(clientRequest.getActive().get());

        }

        // county
         if (clientRequest.getCountyId() != null && clientRequest.getCountyId().isPresent()) {
            Optional<County> county = countyRepository.findById(clientRequest.getCountyId().get());

            if (!county.isPresent()) {
                throw new Exception("Invalid county id");
            }

            
            client.setCounty(county.get());
        }


        // locality
        if (clientRequest.getLocality() != null && clientRequest.getLocality().isPresent()) {
            client.setLocality(clientRequest.getLocality().map(
                    Object::toString).orElse(null));

        }

        clientRepository.save(client);

        return client;
    }

}
