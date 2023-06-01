package com.uhg.cartservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uhg.cartservice.entity.ProductCartMapping;

@Repository
public interface ProductCartMappingRepository extends JpaRepository<ProductCartMapping, Integer> {

	public List<ProductCartMapping> findByCartId(Integer cartId);
	public Optional<ProductCartMapping> findByProductId(Integer productId);
	
}
