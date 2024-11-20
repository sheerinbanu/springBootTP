package com.diginamic.shop.services;

import com.diginamic.shop.entities.Order;
import com.diginamic.shop.repositories.CustomerRepository;
import com.diginamic.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<Order> viewOrders(){
        return orderRepository.findAll();
    }
}
