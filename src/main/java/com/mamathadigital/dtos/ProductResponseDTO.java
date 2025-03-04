package com.mamathadigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
	
	private String productId;

	private String productName;

	private String productColor;

	private String productImage;

	private Double productPrice;

	private Double productDiscount;

	private Integer productLength;

	private Integer productWidth;

	private String productKeyFeatures;

	private String productDescription;
	
	

}
