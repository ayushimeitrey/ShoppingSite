package com.training.demo.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;

	@Column
	private String productName;
	
	@Column
	private String specifications;
	
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Order> orderList=new HashSet<>();
	
	
	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}

	public Product() {

	}

	public Product(String specifications, String productName) {
		super();
		this.specifications = specifications;
		this.productName = productName;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName+ ", specifications=" + specifications 
				+ "]";
	}
}
