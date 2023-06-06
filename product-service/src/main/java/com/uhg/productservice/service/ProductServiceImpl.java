package com.uhg.productservice.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uhg.productservice.entity.Product;
import com.uhg.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Set<Product> filterProductByKeyword(String keyword) {
		Set<Product> products1 = productRepository.findByProductNameContainingIgnoreCase(keyword);
		Set<Product> products2 = productRepository.findByDescriptionContainingIgnoreCase(keyword);
		Set<Product> products = Stream.concat(products1.stream(), products2.stream())
			.collect(Collectors.toSet());
		return products;
	}

}
