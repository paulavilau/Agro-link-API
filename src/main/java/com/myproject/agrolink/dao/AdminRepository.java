
package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByFirebaseId(@RequestParam String firebaseId);
}
