package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Cart;
import com.myproject.agrolink.entity.CartItem;
import com.myproject.agrolink.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCartAndProduct(Cart cart, Product product);
}
