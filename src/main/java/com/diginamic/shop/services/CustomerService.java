package com.diginamic.shop.services;

import com.diginamic.shop.entities.Category;
import com.diginamic.shop.entities.Customer;
import com.diginamic.shop.entities.Order;
import com.diginamic.shop.entities.Product;
import com.diginamic.shop.exceptions.CategoryNotFoundException;
import com.diginamic.shop.exceptions.CustomerNotFoundException;
import com.diginamic.shop.exceptions.OrderNotFoundException;
import com.diginamic.shop.exceptions.ProductNotFoundException;
import com.diginamic.shop.repositories.CustomerRepository;
import com.diginamic.shop.repositories.OrderRepository;
import com.diginamic.shop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Customer> viewCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public Customer addOrderToCustomer(Long customerId, Order order){
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        order.setCustomer(customer);
        customer.getOrders().add(order);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer removeOrderFromCustomer(Long customerId, Long orderId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        if(order.getCustomer().equals(customer)){
            customer.getOrders().remove(order);
            orderRepository.delete(order);
            }else {
            throw new RuntimeException("Order does not belong to the customer");
        } return customerRepository.save(customer);
    }

    @Transactional
    public Customer addProductToCustomer(Long productId, Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.getCustomers().add(customer);
        customer.getProducts().add(product);productRepository.save(product);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer removeProductFromCustomer(Long productId, Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        if(product.getCustomers().contains(customer) && customer.getProducts().contains(product)){
            customer.getProducts().remove(product);
            product.getCustomers().remove(customer);
            productRepository.save(product);
            return customerRepository.save(customer);
        }else{
            throw new RuntimeException("Product not related to this customer");

        }
    }
}
