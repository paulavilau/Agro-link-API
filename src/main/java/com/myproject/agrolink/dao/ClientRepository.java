package com.myproject.agrolink.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByFirebaseId(@RequestParam(name = "firebaseId") String firebaseId);

    Client findByEmail(@RequestParam(name = "email") String email);

    Page<Client> findByFirstNameContaining(@RequestParam("firstName") String firstName, Pageable pageable);

    Page<Client> findByEmailContaining(@RequestParam("email") String email, Pageable pageable);
}
