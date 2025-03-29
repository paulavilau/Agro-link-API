package com.myproject.agrolink.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByClient(Client client);

    Page<Order> findByCompleted(@RequestParam Integer completed, Pageable pageable);
}
