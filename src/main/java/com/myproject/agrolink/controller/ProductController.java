package com.myproject.agrolink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.entity.FavouriteProduct;
import com.myproject.agrolink.entity.Product;
import com.myproject.agrolink.requestmodel.AddProductRequest;
import com.myproject.agrolink.requestmodel.FavouriteProductRequest;
import com.myproject.agrolink.requestmodel.ImageRequest;
import com.myproject.agrolink.requestmodel.ModifyProductRequest;
import com.myproject.agrolink.service.ProductService;

@RestController
@RequestMapping("/agrolink/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> postProduct(@RequestBody AddProductRequest productRequest) throws Exception {
        productService.addProduct(productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modifyProduct")
    public ResponseEntity<String> modProduct(@RequestBody ModifyProductRequest productRequest) throws Exception {
        productService.modifyProduct(productRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/secure/delete/product")
    public void removeProduct(@RequestParam Integer productId) throws Exception {
        productService.deleteProduct(productId);
    }

    @PostMapping("/secure/addToFavourites")
    public FavouriteProduct addToFavourites(@RequestBody FavouriteProductRequest favouriteProductRequest)
            throws Exception {
        return productService.addFavouriteProduct(favouriteProductRequest);
    }

    @PostMapping("/secure/addImage")
    public ResponseEntity<String> addNewImage(@RequestBody ImageRequest imageRequest) throws Exception {
        productService.addProductImage(imageRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Product> fetchProducts(){
        return productService.fetchAllProducts();
    } 

}
