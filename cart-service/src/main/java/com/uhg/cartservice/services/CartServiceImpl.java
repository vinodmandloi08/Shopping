package com.uhg.cartservice.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uhg.cartservice.dto.CartRequestDto;
import com.uhg.cartservice.entity.Cart;
import com.uhg.cartservice.entity.ProductCartMapping;
import com.uhg.cartservice.repository.CartRepository;
import com.uhg.cartservice.repository.ProductCartMappingRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductCartMappingRepository productCartMappingRepository;

	@Autowired
	private CartRequestDto cartRequestDto;

	@Transactional
	@Override
	public void addToCart(CartRequestDto cartRequest) {
		Cart cart = cartRequestDto.convertCartRequestDtoToCart(cartRequest);
		Optional<Cart> findCart = cartRepository.findByUserId(cart.getUserId());
		Cart userCart = null;
		if (findCart.isEmpty()) {
			userCart = cartRepository.save(cart);
		} else {
			userCart = findCart.get();
		}
		Optional<ProductCartMapping> optionalProductCartMapping = productCartMappingRepository
																.findByProductId(cartRequest.getProductId());
		ProductCartMapping savedProductCartMapping = null;
		if (optionalProductCartMapping.isEmpty()) {
			savedProductCartMapping = new ProductCartMapping();
			savedProductCartMapping.setCartId(userCart.getCartId());
			savedProductCartMapping.setProductId(cartRequest.getProductId());
			savedProductCartMapping.setProductQuantity(1);
		} else {
			savedProductCartMapping = optionalProductCartMapping.get();
			savedProductCartMapping.setProductQuantity(savedProductCartMapping.getProductQuantity() + 1);
		}
		productCartMappingRepository.save(savedProductCartMapping);

	}

	@Override
	public List<ProductCartMapping> getCartData(Integer userId) {
		Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
		if(optionalCart.isEmpty()) {
			return null;
		}
		Cart cart = optionalCart.get();
		return productCartMappingRepository.findByCartId(cart.getCartId());
	}

}
