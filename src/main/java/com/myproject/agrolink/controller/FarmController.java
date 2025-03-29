package com.myproject.agrolink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.dao.FarmRepository;
import com.myproject.agrolink.entity.DeliveryCounty;
import com.myproject.agrolink.requestmodel.AddFarmRequest;
import com.myproject.agrolink.requestmodel.AddFarmUserRequest;
import com.myproject.agrolink.requestmodel.DeliveryCountyRequest;
import com.myproject.agrolink.requestmodel.ModifyFarmRequest;
import com.myproject.agrolink.requestmodel.ModifyFarmUserRequest;
import com.myproject.agrolink.service.FarmService;

@RestController
@RequestMapping("/agrolink/farms")

public class FarmController {

    private FarmService farmService;

    private FarmRepository farmRepository;

    public FarmController(FarmService farmService, FarmRepository farmRepository) {
        this.farmService = farmService;
        this.farmRepository = farmRepository;
    }

    @PostMapping("/addFarm")
    public ResponseEntity<String> createFarm(@RequestBody AddFarmRequest farmRequest) throws Exception {
        farmService.addFarm(farmRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/modifyFarm")
    public ResponseEntity<String> modfarm(@RequestBody ModifyFarmRequest farmRequest) throws Exception {
        farmService.modifyFarm(farmRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addFarmUser")
    public ResponseEntity<String> createFarmUser(@RequestBody AddFarmUserRequest farmUserRequest) throws Exception {
        farmService.addFarmUser(farmUserRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modifyFarmUser")
    public ResponseEntity<String> modFarmUser(@RequestBody ModifyFarmUserRequest farmUserRequest) throws Exception {
        farmService.modifyFarmUser(farmUserRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addDeliveryCounty")
    public DeliveryCounty createDeliveryCounty(@RequestBody DeliveryCountyRequest deliveryCountyRequest)
            throws Exception {
        return farmService.addDeliveryCounty(deliveryCountyRequest);
    }

    @GetMapping("/farmsFromCounty")
    public List<Farm> findFarmsFromCounty(@RequestParam Integer countyId, Integer validated) {
        return farmService.findByValidatedAndCountyId(validated, countyId);
    }

    @GetMapping("/findByCode")
    public ResponseEntity<String> findFarmByCode(@RequestParam String code) throws Exception {
        Farm farm = farmRepository.findByCode(code);
        if (farm == null) {
            throw new Exception("Invalid farm code");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
