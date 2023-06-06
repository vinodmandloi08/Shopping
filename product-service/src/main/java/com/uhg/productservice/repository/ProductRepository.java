package com.uhg.productservice.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uhg.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Set<Product> findByProductNameContainingIgnoreCase(String productName);
	Set<Product> findByDescriptionContainingIgnoreCase(String description);

}
