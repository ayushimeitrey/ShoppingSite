package com.training.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.training.demo.model.Order;
import com.training.demo.model.Product;
import com.training.demo.repositories.OrderRepository;
import com.training.demo.repositories.ProductRepository;

@RestController
public class OrderController {

@Autowired
OrderRepository orderRepository;

@Autowired
ProductRepository productRepository;

@GetMapping("/products/{productId}/orders")
public List<Order> getOrders(@PathVariable(value="productId") Long id){
	Optional<Product> product= productRepository.findById(id);
	return orderRepository.findByProduct(product.get());
}

@PostMapping("/products/{productId}/orders")
public Order addOrders(@PathVariable(value="productId") Long id, @RequestBody Order order) throws Exception{
	Optional<Product> product= productRepository.findById(id);
	if(product.isPresent()) {
		order.setProduct(product.get());
		return orderRepository.save(order);
	}
	else {
		throw new Exception("No Product is Present");
	}
}

@PutMapping("/products/{productId}/orders/{orderId}")
public Order editOrder(@PathVariable(value="productId") Long productId, 
						@PathVariable(value="orderId") Long orderId,
						@RequestBody Order order) throws Exception{
	Optional<Order> oldOrder=orderRepository.findById(orderId);
	Optional<Product> oldProduct=productRepository.findById(productId);
	if(oldProduct.isPresent()) {
		if(oldOrder.isPresent()) {
			oldOrder.get().setOrderName(order.getOrderName());
			return orderRepository.save(oldOrder.get());
		}
		else {
			throw new Exception("No such product");
		}
	}
	else {
		throw new Exception("No such product");
	}
}


@DeleteMapping("/products/{productId}/orders/{orderId}")
public void deleteOrder(@PathVariable(value="productId") Long productId, 
						@PathVariable(value="orderId") Long orderId) throws Exception{
	Optional<Order> oldOrder=orderRepository.findById(orderId);
	Optional<Product> oldProduct=productRepository.findById(productId);
	if(oldProduct.isPresent()) {
		if(oldOrder.isPresent()) {
			orderRepository.deleteById(orderId); 
		}
		else {
			throw new Exception("No such product");
		}
	}
	else {
		throw new Exception("No such product");
	}

	}
	
}
