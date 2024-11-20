package com.diginamic.shop.controllers;

import com.diginamic.shop.entities.Category;
import com.diginamic.shop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Iterable<Category> viewCategories(){
        return  categoryService.viewCategory();
    }
    @PostMapping("/createCategory")
    public Category createCategory(@RequestParam String name){
        return categoryService.createCategory(name);
    }

}
