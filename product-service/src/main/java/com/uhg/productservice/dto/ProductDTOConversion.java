package com.uhg.productservice.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.uhg.productservice.entity.Product;

@Component
public class ProductDTOConversion {

	public Product convertProductDTOToProduct(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}
	
	public ProductDTO convertProductToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}
}
