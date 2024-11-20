package com.diginamic.shop.services;

import com.diginamic.shop.entities.Category;
import com.diginamic.shop.entities.Product;
import com.diginamic.shop.exceptions.CategoryNotFoundException;
import com.diginamic.shop.exceptions.ProductCreationException;
import com.diginamic.shop.repositories.CategoryRepository;
import com.diginamic.shop.repositories.CustomerRepository;
import com.diginamic.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<Product> viewProducts() {
        return productRepository.findAll();
    }

    public List<Product> findByNameAndPrice(String name, double price) {
        return productRepository.findByNameAndPrice(name, price);
    }

    public Product createProduct(String name, String categoryName) {
        //validation des paramètres
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (categoryName == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        // Récupération de la catégorie depuis la base de données
        Category category = categoryRepository.findByName(categoryName).orElseThrow(CategoryNotFoundException::new);

        //création et persistance du produit
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ProductCreationException();
        }
    }
}
