package com.uhg.productservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.uhg.productservice.entity.Product;

@Service
public interface ProductService {
	
	public List<Product> getAllProducts();
	public Product addProduct(Product product);
	public Set<Product> filterProductByKeyword(String keyword);

}
