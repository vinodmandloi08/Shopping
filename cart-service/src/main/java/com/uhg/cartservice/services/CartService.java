package com.uhg.cartservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uhg.cartservice.dto.CartRequestDto;
import com.uhg.cartservice.entity.ProductCartMapping;

@Service
public interface CartService {
	
	public void addToCart(CartRequestDto cartRequestDto); 
	public List<ProductCartMapping> getCartData(Integer userId);

}
