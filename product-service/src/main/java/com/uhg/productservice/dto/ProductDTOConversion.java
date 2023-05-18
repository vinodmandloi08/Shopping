package com.uhg.productservice.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.uhg.productservice.entity.Product;

@Component
public class ProductDTOConversion {

	public static Product convertProductDTOToProduct(ProductDTO productDTO) {
		Product product = new Product();
		System.out.println(productDTO.getImage());
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}
	
	public static ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}
}
