package com.myproject.agrolink.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.agrolink.dao.CategoryRepository;
import com.myproject.agrolink.dao.SubcategoryRepository;
import com.myproject.agrolink.entity.Category;
import com.myproject.agrolink.entity.Subcategory;
import com.myproject.agrolink.requestmodel.AddCategoryRequest;
import com.myproject.agrolink.requestmodel.AddSubcategoryRequest;
import com.myproject.agrolink.requestmodel.ModifyCategoryRequest;
import com.myproject.agrolink.requestmodel.ModifySubcategoryRequest;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    private SubcategoryRepository subcategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public Category findCategoryById(Integer categoryId) throws Exception {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (!category.isPresent()) {
            throw new Exception("Category doesn t exist");
        }

        return category.get();
    }

    public Subcategory findSubcategoryById(Integer subcategoryId) throws Exception {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(subcategoryId);

        if (!subcategory.isPresent()) {
            throw new Exception("Subcategory doesn t exist");
        }

        return subcategory.get();
    }

    public Category createCategory(AddCategoryRequest addCategoryRequest) {
        Category category = new Category();

        category.setName(addCategoryRequest.getName());

        category.setImageLink(addCategoryRequest.getImageLink());

        categoryRepository.save(category);

        return category;
    }

    public Subcategory createSubcategory(AddSubcategoryRequest addSubcategoryRequest) throws Exception {
        Optional<Category> category = categoryRepository.findById(addSubcategoryRequest.getCategoryId());

        if (!category.isPresent()) {
            throw new Exception("Category doesn't exist");
        }

        Subcategory subcategory = new Subcategory();

        subcategory.setName(addSubcategoryRequest.getName());

        subcategory.setImageLink(addSubcategoryRequest.getImageLink());

        subcategory.setCategory(category.get());

        subcategoryRepository.save(subcategory);

        return subcategory;
    }

    public Category modifyCategory(ModifyCategoryRequest modifyCategoryRequest) throws Exception {
        Category category = findCategoryById(modifyCategoryRequest.getId());

        // name
        if (modifyCategoryRequest.getName() != null && modifyCategoryRequest.getName().isPresent()) {
            category.setName(modifyCategoryRequest.getName().map(
                    Object::toString).orElse(null));
        }

        // imageLink
        if (modifyCategoryRequest.getImageLink() != null && modifyCategoryRequest.getImageLink().isPresent()) {
            category.setImageLink(modifyCategoryRequest.getImageLink().map(
                    Object::toString).orElse(null));
        }

        return category;
    }

    public Subcategory modifySubcategory(ModifySubcategoryRequest modifySubcategoryRequest) throws Exception {
        Subcategory subcategory = findSubcategoryById(modifySubcategoryRequest.getId());

        // name
        if (modifySubcategoryRequest.getName() != null && modifySubcategoryRequest.getName().isPresent()) {
            subcategory.setName(modifySubcategoryRequest.getName().map(
                    Object::toString).orElse(null));
        }

        // imageLink
        if (modifySubcategoryRequest.getImageLink() != null && modifySubcategoryRequest.getImageLink().isPresent()) {
            subcategory.setImageLink(modifySubcategoryRequest.getImageLink().map(
                    Object::toString).orElse(null));
        }

        return subcategory;
    }
}
