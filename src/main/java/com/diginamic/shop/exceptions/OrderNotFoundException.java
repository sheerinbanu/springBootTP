package com.diginamic.shop.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {

      super("Order not found", new Throwable("Order not found"));
    }
}
