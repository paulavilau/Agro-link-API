package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.FarmUser;

public interface FarmUserRepository extends JpaRepository<FarmUser, Integer> {
    FarmUser findByFirebaseId(@RequestParam String firebaseId);
}
