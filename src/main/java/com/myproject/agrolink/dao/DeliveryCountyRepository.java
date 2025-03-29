package com.myproject.agrolink.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.agrolink.entity.County;
import com.myproject.agrolink.entity.DeliveryCounty;
import com.myproject.agrolink.entity.Farm;

public interface DeliveryCountyRepository extends JpaRepository<DeliveryCounty, Integer> {

    DeliveryCounty findByFarmAndCounty(Farm farm, County county);
}
