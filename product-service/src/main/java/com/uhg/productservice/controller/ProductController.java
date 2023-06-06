package com.uhg.productservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uhg.productservice.dto.ProductDTO;
import com.uhg.productservice.dto.ProductDTOConversion;
import com.uhg.productservice.entity.Product;
import com.uhg.productservice.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired private ProductService productService;

	@GetMapping("/all-products")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		List<ProductDTO> productDTOs = products.stream()
                .map(ProductDTOConversion::convertProductToProductDTO)
                .collect(Collectors.toList());
		return ResponseEntity.ok(productDTOs);
	}
	
	@PostMapping("/add-product")
	public ResponseEntity<ProductDTO> addProduct(@RequestParam("productName") String productName,
									             @RequestParam("description") String description,
									             @RequestParam("unitPrice") Double unitPrice,
									             @RequestParam("image") MultipartFile imageFile,
									             @RequestParam("quantity") Integer quantity){
		try {
			byte[] imageBytes = null;
            if (!imageFile.isEmpty()) {
                imageBytes = imageFile.getBytes();
            }

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(productName);
            productDTO.setDescription(description);
            productDTO.setUnitPrice(unitPrice);
            productDTO.setImage(imageBytes);
            productDTO.setQuantity(quantity);
			Product product = productService.addProduct(ProductDTOConversion.convertProductDTOToProduct(productDTO));
			return ResponseEntity.ok(ProductDTOConversion.convertProductToProductDTO(product));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/filter-product")
	public ResponseEntity<Set<Product>> filterPorduct(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(productService.filterProductByKeyword(keyword));
	}
}
