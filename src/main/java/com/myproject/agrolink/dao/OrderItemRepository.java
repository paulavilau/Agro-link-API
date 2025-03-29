package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Order;
import com.myproject.agrolink.entity.OrderItem;
import com.myproject.agrolink.entity.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    OrderItem findByOrderAndProduct(Order order, Product product);
}
