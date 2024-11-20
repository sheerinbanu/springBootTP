package com.diginamic.shop.controllers;

import com.diginamic.shop.entities.Product;
import com.diginamic.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Iterable<Product> viewProducts(){
        return productService.viewProducts();
    }

    @GetMapping("/searchByNamePrice")
    public Iterable<Product> searchByNameAndPrice(@RequestParam String name, @RequestParam double price){
        return productService.findByNameAndPrice(name, price);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestParam String name, @RequestParam String categoryName){
        return productService.createProduct(name, categoryName);
    }
}
