package com.myproject.agrolink.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myproject.agrolink.dao.CountyRepository;
import com.myproject.agrolink.dao.FarmUserRepository;
import com.myproject.agrolink.dao.ImageRepository;
import com.myproject.agrolink.dao.DeliveryCountyRepository;
import com.myproject.agrolink.dao.FarmRepository;
import com.myproject.agrolink.entity.County;
import com.myproject.agrolink.entity.FarmUser;
import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.entity.DeliveryCounty;
import com.myproject.agrolink.requestmodel.AddFarmRequest;
import com.myproject.agrolink.requestmodel.AddFarmUserRequest;
import com.myproject.agrolink.requestmodel.DeliveryCountyRequest;
import com.myproject.agrolink.requestmodel.ModifyFarmRequest;
import com.myproject.agrolink.requestmodel.ModifyFarmUserRequest;

@Service
@Transactional
public class FarmService {

    private FarmRepository farmRepository;

    private FarmUserRepository farmUserRepository;

    private DeliveryCountyRepository deliveryCountyRepository;

    private CountyRepository countyRepository;

    private ImageRepository imageRepository;

    public FarmService(ImageRepository imageRepository, FarmUserRepository farmUserRepository,
            FarmRepository farmRepository, DeliveryCountyRepository deliveryCountyRepository,
            CountyRepository countyRepository) {
        this.farmRepository = farmRepository;
        this.deliveryCountyRepository = deliveryCountyRepository;
        this.countyRepository = countyRepository;
        this.farmUserRepository = farmUserRepository;
        this.imageRepository = imageRepository;
    }

    public Farm getFarmById(Integer farmId) throws Exception {
        Optional<Farm> farm = farmRepository.findById(farmId);
        if (farm.isPresent()) {
            return farm.get();
        } else {
            throw new Exception("Cart with id " + farmId + " not found");
        }

    }

    public FarmUser getFarmUserById(Integer farmUserId) throws Exception {
        Optional<FarmUser> farmUser = farmUserRepository.findById(farmUserId);
        if (farmUser.isPresent()) {
            return farmUser.get();
        } else {
            throw new Exception("Cart with id " + farmUserId + " not found");
        }

    }

    public Farm addFarm(AddFarmRequest farmRequest) throws Exception {
        Optional<County> county = countyRepository.findById(farmRequest.getCountyId());

        if (!county.isPresent()) {
            throw new Exception("Invalid county id");
        }

        Farm farm = new Farm();

        // name
        farm.setName(farmRequest.getName());

        // locality
        farm.setLocality(farmRequest.getLocality());

        // county
        farm.setCounty(county.get());

        // address
        if (farmRequest.getAddress() != null && farmRequest.getAddress().isPresent()) {
            farm.setAddress(farmRequest.getAddress().map(
                    Object::toString).orElse(null));

        }

        // phone
        farm.setPhone(farmRequest.getPhone());

        // email
        farm.setEmail(farmRequest.getEmail());

        // email
        farm.setCreationDt(LocalDateTime.now());

        // validated
        farm.setValidated(0);

        farmRepository.save(farm);

        return farm;
    }

    public Farm modifyFarm(ModifyFarmRequest farmRequest) throws Exception {
        Farm validateFarm = getFarmById(farmRequest.getId());

        if (validateFarm == null) {
            throw new Exception("Farm id invalid");
        }

        // name
        if (farmRequest.getName() != null && farmRequest.getName().isPresent()) {
            validateFarm.setName(farmRequest.getName().map(
                    Object::toString).orElse(null));

        }

        // locality
         if (farmRequest.getLocality() != null && farmRequest.getLocality().isPresent()) {
            validateFarm.setLocality(farmRequest.getLocality().map(
                    Object::toString).orElse(null));

        }

        if (farmRequest.getCountyId() != null && farmRequest.getCountyId().isPresent()) {
            Optional<County> county = countyRepository.findById(farmRequest.getCountyId().get());

            if (!county.isPresent()) {
                throw new Exception("Invalid county id");
            }

            // county
            validateFarm.setCounty(county.get());

        }
        // address
        if (farmRequest.getAddress() != null && farmRequest.getAddress().isPresent()) {
            validateFarm.setAddress(farmRequest.getAddress().map(
                    Object::toString).orElse(null));

        }

        // description
        if (farmRequest.getDescription() != null && farmRequest.getDescription().isPresent()) {
            validateFarm.setDescription(farmRequest.getDescription().map(
                    Object::toString).orElse(null));

        }

        // image
        if (farmRequest.getImage() != null && farmRequest.getImage().isPresent()) {
            validateFarm.setImage(farmRequest.getImage().map(
                    Object::toString).orElse(null));

        }

        // validated
        if(farmRequest.getValidated() != null && farmRequest.getValidated().isPresent()){
            final Integer value = farmRequest.getValidated().get();
            if (value == 1) {
                int length = 10;
                String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                Random rnd = new Random();

                StringBuilder sb = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
                }

                String randomString = sb.toString();
                System.out.println(randomString);

                validateFarm.setCode(randomString);

                validateFarm.setAdmin(null);
            }

            validateFarm.setValidationDt(LocalDateTime.now());

            validateFarm.setValidated(farmRequest.getValidated().get());
        }

        // phone
        if (farmRequest.getPhone() != null && farmRequest.getPhone().isPresent()) {
            validateFarm.setPhone(farmRequest.getPhone().map(
                    Object::toString).orElse(null));

        }

        // email
        if (farmRequest.getEmail() != null && farmRequest.getEmail().isPresent()) {
            validateFarm.setEmail(farmRequest.getEmail().map(
                    Object::toString).orElse(null));

        }

        // email
        if (farmRequest.getDeliveryPrice() != null && farmRequest.getDeliveryPrice().isPresent()) {
            validateFarm.setDeliveryPrice(farmRequest.getDeliveryPrice().get());

        }

        farmRepository.save(validateFarm);

        return validateFarm;
    }

    public FarmUser addFarmUser(AddFarmUserRequest farmUserRequest) throws Exception {
        Optional<Farm> farm = farmRepository.findById(farmUserRequest.getFarmId());

        System.out.println(farmUserRequest.getFarmId());
        if (farm == null) {
            throw new Exception("Invalid farm id");
        }

        FarmUser farmUser = new FarmUser();

        farmUser.setEmail(farmUserRequest.getEmail());

        farmUser.setName(farmUserRequest.getName());

        farmUser.setRole(farmUserRequest.getRole());

        farmUser.setValidated(0);

        farmUser.setFirebaseId(farmUserRequest.getFirebaseId());

        farmUser.setFarm(farm.get());

        farmUserRepository.save(farmUser);

        return farmUser;
    }

        public void modifyFarmUser(ModifyFarmUserRequest farmUserRequest) throws Exception {
            FarmUser farmUser = getFarmUserById(farmUserRequest.getId());

            // email
            if (farmUserRequest.getEmail() != null && farmUserRequest.getEmail().isPresent()) {
                farmUser.setEmail(farmUserRequest.getEmail().map(
                        Object::toString).orElse(null));

            }

            // name
            if (farmUserRequest.getName() != null && farmUserRequest.getName().isPresent()) {
                farmUser.setName(farmUserRequest.getName().map(
                        Object::toString).orElse(null));

            }

            // validated
            if (farmUserRequest.getValidated() != null && farmUserRequest.getValidated().isPresent()) {
                farmUser.setValidated(farmUserRequest.getValidated().get());

            }

            farmUserRepository.save(farmUser);
        }

    public DeliveryCounty addDeliveryCounty(DeliveryCountyRequest deliveryCountyRequest) throws Exception {
        Farm farm = getFarmById(deliveryCountyRequest.getFarmId());

        Optional<County> county = countyRepository.findById(deliveryCountyRequest.getCountyId());

        if (!county.isPresent()) {
            throw new Exception("Id judet invalid ");
        }

        // Verificare daca judetul este deja adaugat in lisat de judete a fermei
        DeliveryCounty deliveryCounty = deliveryCountyRepository.findByFarmAndCounty(farm, county.get());

        if (deliveryCounty != null) {
            throw new Exception("County already added to the list of delivery counties");
        } else {
            // Create judet livrare nou daca nu este deja adaugat
            DeliveryCounty newDeliveryCounty = new DeliveryCounty();
            newDeliveryCounty.setFarm(farm);
            newDeliveryCounty.setCounty(county.get());
            deliveryCountyRepository.save(newDeliveryCounty);

            System.out.println(newDeliveryCounty.getId());

            return newDeliveryCounty;
        }
    }

    public List<Farm> findByValidatedAndCountyId(Integer validated, Integer countyId) {
        Optional<County> county = countyRepository.findById(countyId);
 
        List<Farm> farms = farmRepository.findByCountyAndValidated(county.get(), validated);

        return farms;
    }

}