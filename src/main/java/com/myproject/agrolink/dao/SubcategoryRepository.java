package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
}
