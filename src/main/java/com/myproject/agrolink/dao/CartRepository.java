package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Cart;
import com.myproject.agrolink.entity.Client;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByClient(Client client);
}
