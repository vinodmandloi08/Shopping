package com.uhg.productservice.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long productId;
	
	@NotBlank
	private String productName;
	@NotBlank
	private String description;
	@NotNull
	private Double unitPrice;
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	@NotNull
	private byte[] image;
	@NotNull
	private Integer quantity;

}
