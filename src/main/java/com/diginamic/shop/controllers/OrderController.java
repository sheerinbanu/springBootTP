package com.diginamic.shop.controllers;

import com.diginamic.shop.entities.Order;
import com.diginamic.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public Iterable<Order> viewOrders(){
        return orderService.viewOrders();
    }
}
