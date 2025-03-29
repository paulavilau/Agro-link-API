package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}