package com.training.demo.repositories;

import org.springframework.stereotype.Repository;

import com.training.demo.model.Order;
import com.training.demo.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	public List<Order> findByProduct(Product product);

}
