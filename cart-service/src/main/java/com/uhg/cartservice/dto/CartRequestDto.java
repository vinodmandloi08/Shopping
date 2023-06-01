package com.uhg.cartservice.dto;

import org.springframework.stereotype.Component;

import com.uhg.cartservice.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CartRequestDto {
	
	private Integer userId;
	private Integer productId;
	
	public Cart convertCartRequestDtoToCart(CartRequestDto cartRequestDto) {
		Cart cart = new Cart();
		cart.setUserId(cartRequestDto.getUserId());
		return cart;
	}

}
