package com.uhg.cartservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uhg.cartservice.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public Optional<Cart> findByUserId(Integer userId);

}
