package com.diginamic.shop.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class})
    protected ResponseEntity<Object> handleCategoryNotFound(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "Category not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    protected ResponseEntity<Object> handleProductNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    protected ResponseEntity<Object> handleCustomerNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Customer not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({OrderNotFoundException.class})
    protected ResponseEntity<Object> handleOrderNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Order not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    protected ResponseEntity<Object> handleProductCreation(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Product cannot be created", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
