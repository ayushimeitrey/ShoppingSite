package com.training.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.demo.model.Order;
import com.training.demo.model.Product;
import com.training.demo.repositories.OrderRepository;
import com.training.demo.repositories.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/products/{productId}")
	public Product editProduct(@PathVariable(value="productId") Long id, @RequestBody Product product) throws Exception{
		Optional<Product> oldProduct=productRepository.findById(id);
		if(oldProduct.isPresent()) {
			oldProduct.get().setProductName(product.getProductName());
			oldProduct.get().setSpecifications(product.getSpecifications());
			//oldProduct.get().setOrderList(product.getOrderList());
			return productRepository.save(oldProduct.get());
		}
		else {
			throw new Exception("No such product");
		}
	}
	
	@DeleteMapping("/products/{productId}")
	public void deleteProduct(@PathVariable(value="productId") Long id) throws Exception{
		Optional<Product> oldProduct=productRepository.findById(id);
		if(oldProduct.isPresent()) {
			 productRepository.delete(oldProduct.get());;
		}
		else {
			throw new Exception("No such product");
		}
	}

}
