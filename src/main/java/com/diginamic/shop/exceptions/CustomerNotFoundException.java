package com.diginamic.shop.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {

      super("Customer not found", new Throwable("Customer not found"));
    }
}
