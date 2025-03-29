package com.myproject.agrolink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.requestmodel.AddClientRequest;
import com.myproject.agrolink.requestmodel.ModifyClientRequest;
import com.myproject.agrolink.service.ClientService;

@RestController
@RequestMapping("/agrolink/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/addClient")
    public Client postClient(@RequestBody AddClientRequest clientRequest) throws Exception {
        return clientService.addClient(clientRequest);

        // return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modifyClient")
    public ResponseEntity<String> modClient(@RequestBody ModifyClientRequest clientRequest) throws Exception {
        clientService.modifyClient(clientRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
