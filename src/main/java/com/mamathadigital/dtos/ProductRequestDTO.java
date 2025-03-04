package com.mamathadigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductRequestDTO {
	
	private String categoryId;

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
