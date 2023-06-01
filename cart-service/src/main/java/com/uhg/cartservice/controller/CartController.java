package com.uhg.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uhg.cartservice.dto.CartRequestDto;
import com.uhg.cartservice.entity.ProductCartMapping;
import com.uhg.cartservice.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<Void> addToCart(@RequestBody CartRequestDto cartRequestDto){
		cartService.addToCart(cartRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/cart-data")
	public ResponseEntity<List<ProductCartMapping>> getCartData(@RequestParam Integer userId){
		List<ProductCartMapping> cartData = cartService.getCartData(userId);
		if(cartData==null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cartData);
	}
}