package com.diginamic.shop.exceptions;

public class ProductCreationException extends RuntimeException {
    public ProductCreationException() {

      super("Product cannot be created", new Throwable("product cannot be created"));
    }
}
