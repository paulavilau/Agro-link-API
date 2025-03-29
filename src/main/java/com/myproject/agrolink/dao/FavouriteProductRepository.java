package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.FavouriteProduct;
import com.myproject.agrolink.entity.Product;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Integer> {
    FavouriteProduct findByProductAndClient(Product product, Client client);
}
