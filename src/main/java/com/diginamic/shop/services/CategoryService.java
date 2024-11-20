package com.diginamic.shop.services;

import com.diginamic.shop.entities.Category;
import com.diginamic.shop.entities.Product;
import com.diginamic.shop.exceptions.CategoryNotFoundException;
import com.diginamic.shop.exceptions.ProductNotFoundException;
import com.diginamic.shop.repositories.CategoryRepository;
import com.diginamic.shop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> viewCategory(){
        return categoryRepository.findAll();
    }

    public Category createCategory(String name) {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("La catégorie avec ce nom existe déjà.");
        }
            Category category = new Category();
            category.setName(name);
            return categoryRepository.save(category);
    }

    @Transactional
    public Category addProductToCategory( Long categoryId, Product product){
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        product.setCategory(category);
        category.getProducts().add(product);
        productRepository.save(product);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category removeProductFromCategory(Long categoryId, Long productId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        if(product.getCategory().equals(category)){
            category.getProducts().remove(product);
            productRepository.delete(product);
        }else{
            throw  new RuntimeException("Product does not belong to the category");
        }
        return categoryRepository.save(category);
    }
}
