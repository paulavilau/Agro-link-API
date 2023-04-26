package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCartIdAndProductId(Integer cartId, Integer productId);
}
