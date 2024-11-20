package com.diginamic.shop.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {

      super("Category not found", new Throwable("Category not found"));
    }
}
