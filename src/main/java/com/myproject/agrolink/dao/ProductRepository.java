package com.myproject.agrolink.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

    Product findByCode(@RequestParam(name = "code") String code);

    // @Query("SELECT p FROM Product p JOIN FETCH p.farm")
    // List<Product> findAllProductsAndTheirFarmers();
}
