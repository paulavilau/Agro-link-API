package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.entity.Order;
import com.myproject.agrolink.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    OrderStatus findByOrderAndFarm(Order order, Farm farm);
}
