package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.County;

public interface CountyRepository extends JpaRepository<County, Integer> {

}