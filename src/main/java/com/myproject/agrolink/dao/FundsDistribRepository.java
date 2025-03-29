package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.FundsDistrib;
import com.myproject.agrolink.entity.OrderStatus;

public interface FundsDistribRepository extends JpaRepository<FundsDistrib, Integer> {
    FundsDistrib findByOrderStatus(OrderStatus orderStatus);
}
