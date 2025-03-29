package com.myproject.agrolink.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.agrolink.entity.County;
import com.myproject.agrolink.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Integer> {

    Farm findByCode(@RequestParam String code);

    Farm findByName(@RequestParam String name);

    Page<Farm> findByValidated(@RequestParam Integer validated, Pageable pageable);

    List<Farm> findByCountyAndValidated(County county, Integer validated);

    Page<Farm> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

    Page<Farm> findByEmailContaining(@RequestParam("email") String email, Pageable pageable);

}
