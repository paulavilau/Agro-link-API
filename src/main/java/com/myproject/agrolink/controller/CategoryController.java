package com.myproject.agrolink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.agrolink.requestmodel.AddCategoryRequest;
import com.myproject.agrolink.requestmodel.AddSubcategoryRequest;
import com.myproject.agrolink.requestmodel.ModifyCategoryRequest;
import com.myproject.agrolink.requestmodel.ModifySubcategoryRequest;
import com.myproject.agrolink.service.CategoryService;

@RestController
@RequestMapping("/agrolink/categories")
public class CategoryController {

    private CategoryService categoriesService;

    @Autowired
    public CategoryController(CategoryService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/secure/category")
    public ResponseEntity<String> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        categoriesService.createCategory(addCategoryRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/secure/subcategory")
    public ResponseEntity<String> addSubcategory(@RequestBody AddSubcategoryRequest addSubcategoryRequest)
            throws Exception {
        categoriesService.createSubcategory(addSubcategoryRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/secure/modify-category")
    public ResponseEntity<String> modCategory(@RequestBody ModifyCategoryRequest modifyCategoryRequest)
            throws Exception {
        categoriesService.modifyCategory(modifyCategoryRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/secure/modify-subcategory")
    public ResponseEntity<String> modSubcategory(@RequestBody ModifySubcategoryRequest modifySubcategoryRequest)
            throws Exception {
        categoriesService.modifySubcategory(modifySubcategoryRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
