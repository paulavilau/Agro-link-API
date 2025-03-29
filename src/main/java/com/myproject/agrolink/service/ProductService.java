package com.myproject.agrolink.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.agrolink.dao.CartItemRepository;
import com.myproject.agrolink.dao.ClientRepository;
import com.myproject.agrolink.dao.FarmRepository;
import com.myproject.agrolink.dao.FarmUserRepository;
import com.myproject.agrolink.dao.FavouriteProductRepository;
import com.myproject.agrolink.dao.ImageRepository;
import com.myproject.agrolink.dao.ProductRepository;
import com.myproject.agrolink.dao.SubcategoryRepository;
import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.Farm;
import com.myproject.agrolink.entity.FarmUser;
import com.myproject.agrolink.entity.FavouriteProduct;
import com.myproject.agrolink.entity.Image;
import com.myproject.agrolink.entity.Product;
import com.myproject.agrolink.entity.Subcategory;
import com.myproject.agrolink.requestmodel.AddProductRequest;
import com.myproject.agrolink.requestmodel.FavouriteProductRequest;
import com.myproject.agrolink.requestmodel.ImageRequest;
import com.myproject.agrolink.requestmodel.ModifyProductRequest;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    private FavouriteProductRepository favouriteProductRepository;

    private SubcategoryRepository subcategoryRepository;

    private FarmRepository farmRepository;

    private ImageRepository imageRepository;

    private FarmUserRepository farmUserRepository;

    private ClientRepository clientRepository;

    public ProductService(FarmUserRepository farmUserRepository, FavouriteProductRepository favouriteProductRepository,
            ClientRepository clientRepository, ImageRepository imageRepository, ProductRepository productRepository,
            CartItemRepository cartItemRepository, SubcategoryRepository subcategoryRepository,
            FarmRepository farmRepository) {
        this.productRepository = productRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.farmRepository = farmRepository;
        this.imageRepository = imageRepository;
        this.imageRepository = imageRepository;
        this.clientRepository = clientRepository;
        this.favouriteProductRepository = favouriteProductRepository;
        this.farmUserRepository = farmUserRepository;
    }

    public Product findProductById(Integer productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new Exception("Product with id " + productId + " not found");
        }

    }

    // Adaugare produs

    public Product addProduct(AddProductRequest productRequest) throws Exception {
        // Product validateProduct = productRepository.findByCode(productRequest.getCode());

        // if (validateProduct != null) {
        //     throw new Exception("Product already added with code - " + validateProduct.getCode());
        // }

        Optional<FarmUser> farmUser = farmUserRepository.findById(productRequest.getFarmUserId());

        if (!farmUser.isPresent()) {
            throw new Exception("Farm user doesn t exist");
        }

        Product product = new Product();

        // Code
        product.setCode(productRequest.getCode());

        // Name
        product.setName(productRequest.getName());

        // Creation farm user
        product.setCreationFarmUser(farmUser.get());

        // Creation date time
        product.setCreationDt(LocalDateTime.now());

        // Farm user last mod
        product.setLastModFarmUser(farmUser.get());

        // last mod date time
        product.setLastModDt(LocalDateTime.now());

        // Description
        if (productRequest.getDescription() != null && productRequest.getDescription().isPresent()) {
            product.setDescription(productRequest.getDescription().map(
                    Object::toString).orElse(null));

        }

        // Unit
        product.setUnit(productRequest.getUnit());

        // Measure
        if (productRequest.getMeasure() != null && productRequest.getMeasure().isPresent()) {
            product.setMeasure(productRequest.getMeasure().map(
                    Object::toString).orElse(null));

        }

        // Price
        product.setPrice(productRequest.getPrice());

        // Category
        Optional<Subcategory> subcategory = subcategoryRepository.findById(productRequest.getSubcategoryId());

        if (!subcategory.isPresent()) {
            throw new Exception("Invalid subcategory id");
        }

        product.setSubcategory(subcategory.get());

        // Farmer
        Optional<Farm> farm = farmRepository.findById(productRequest.getFarmId());

        if (!farm.isPresent()) {
            throw new Exception("Invalid farmer id");
        }

        product.setFarm(farm.get());

        // In stock
        if (productRequest.getInStock() != null && productRequest.getInStock().isPresent()) {
            product.setInStock(productRequest.getInStock().get());

        }

        productRepository.save(product);
        return product;
    }



    public void modifyProduct(ModifyProductRequest productRequest) throws Exception {
        Product product = findProductById(productRequest.getProductId());

        Optional<FarmUser> farmUser = farmUserRepository.findById(productRequest.getFarmUserId());

        if (!farmUser.isPresent()) {
            throw new Exception("Farm user doesn t exist");
        }

        // Code
        if (productRequest.getCode() != null && productRequest.getCode().isPresent()) {
            product.setCode(productRequest.getCode().map(
                    Object::toString).orElse(null));
        }

        // Name
        if (productRequest.getName() != null && productRequest.getName().isPresent()) {
            product.setName(productRequest.getName().map(
                    Object::toString).orElse(null));
        }

        // Farm user last mod
        product.setLastModFarmUser(farmUser.get());

        // last mod date time
        product.setLastModDt(LocalDateTime.now());

        // Description
        if (productRequest.getDescription() != null && productRequest.getDescription().isPresent()) {
            product.setDescription(productRequest.getDescription().map(
                    Object::toString).orElse(null));

        }

        // Unit
        if (productRequest.getUnit() != null && productRequest.getUnit().isPresent()) {
            product.setUnit(productRequest.getUnit().map(
                    Object::toString).orElse(null));

        }

        // Measure
        if (productRequest.getMeasure() != null && productRequest.getMeasure().isPresent()) {
            product.setMeasure(productRequest.getMeasure().map(
                    Object::toString).orElse(null));

        }

        // Price
        if (productRequest.getPrice() != null && productRequest.getPrice().isPresent()) {
            product.setPrice(productRequest.getPrice().get());
        }

        // Category
        if (productRequest.getSubcategoryId() != null && productRequest.getSubcategoryId().isPresent()) {
            Optional<Subcategory> subcategory = subcategoryRepository.findById(productRequest.getSubcategoryId().get());

            if (!subcategory.isPresent()) {
                throw new Exception("Invalid subcategory id");
            }

            product.setSubcategory(subcategory.get());
        }

        // InStock
        if (productRequest.getInStock() != null && productRequest.getInStock().isPresent()) {
            product.setInStock(productRequest.getInStock().get());
        }


    }

    public void deleteProduct(Integer productId) throws Exception {

        Optional<Product> product = productRepository.findById(productId);

        if (!product.isPresent()) {
            throw new Exception("Product not found");
        }

        productRepository.delete(product.get());
    }

    public void addProductImage(ImageRequest imageRequest) throws Exception {
        Product product = findProductById(imageRequest.getProductId());

        Optional<FarmUser> farmUser = farmUserRepository.findById(imageRequest.getFarmUserId());

        if(!farmUser.isPresent()){
            throw new Exception("Farm user not found");
        }

        Image image = new Image();

        image.setLink(imageRequest.getLink());

        image.setProduct(product);

        product.setLastModFarmUser(farmUser.get());

        product.setLastModDt(LocalDateTime.now());

        imageRepository.save(image);

    }

    public FavouriteProduct addFavouriteProduct(FavouriteProductRequest favouriteProductRequest) throws Exception {
        Optional<Product> product = productRepository.findById(favouriteProductRequest.getProductId());

        if (!product.isPresent()) {
            throw new Exception("Product doesn't exist.");
        }

        Optional<Client> client = clientRepository.findById(favouriteProductRequest.getClientId());

        if (!client.isPresent()) {
            throw new Exception("Client doesn't exist.");
        }

        FavouriteProduct validateFavouriteProduct = favouriteProductRepository.findByProductAndClient(product.get(),
                client.get());

        if (validateFavouriteProduct != null) {
            throw new Exception("Product already added to favourites");
        }

        FavouriteProduct favouriteProduct = new FavouriteProduct();

        favouriteProduct.setProduct(product.get());

        favouriteProduct.setClient(client.get());

        favouriteProductRepository.save(favouriteProduct);

        return favouriteProduct;
    }

    public List<Product> fetchAllProducts(){
        return productRepository.findAll();
    }

}
