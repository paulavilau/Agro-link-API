package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
