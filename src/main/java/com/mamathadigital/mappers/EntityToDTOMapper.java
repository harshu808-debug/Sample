package com.mamathadigital.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.mamathadigital.dtos.CategoryResponse;
import com.mamathadigital.dtos.ProductRequestDTO;
import com.mamathadigital.dtos.ProductResponseDTO;
import com.mamathadigital.dtos.UserResponseDTO;
import com.mamathadigital.entities.Category;
import com.mamathadigital.entities.Product;
import com.mamathadigital.entities.User;

public class EntityToDTOMapper {
	
	public static ProductResponseDTO toProductResponseDTO(Product product)
	{
		return new ProductResponseDTO(product.getProductId(),
				                   product.getProductName(),
				                   product.getProductColor(), 
				                   product.getProductImage(), 
				                   product.getProductPrice(), 
				                   product.getProductDiscount(),
				                   product.getProductLength(),
				                   product.getProductWidth(), 
				                   product.getProductKeyFeatures(),
				                   product.getProductDescription());
	}
	
	public static Product toProduct(ProductRequestDTO productRequestDTO)
	{
	   return 	new Product(
			    productRequestDTO.getProductName(),
				productRequestDTO.getProductColor(),
				productRequestDTO.getProductImage(),
				productRequestDTO.getProductPrice(),
				productRequestDTO.getProductDiscount(),
				productRequestDTO.getProductLength(),
				productRequestDTO.getProductWidth(),
				productRequestDTO.getProductKeyFeatures(),
				productRequestDTO.getProductDescription()
				);
		
	}
	
	public static CategoryResponse toCategoryResponse(Category category)
	{
		if(category.getProducts()!=null)
		{
			List<ProductResponseDTO> categoryResponses = category
			                                .getProducts()
			                                .stream()
			                                .map(EntityToDTOMapper :: toProductResponseDTO)
			                                .collect(Collectors.toList());
			return new CategoryResponse(category.getCategoryId(), category.getCategoryName(),categoryResponses);
		}
		return new CategoryResponse(category.getCategoryId(), category.getCategoryName(),null);
	}
	
	
	public static UserResponseDTO toUserResponseDTO(User user)
	{
		  UserResponseDTO userResponseDTO = new UserResponseDTO();
		  BeanUtils.copyProperties(user, userResponseDTO);
		  return userResponseDTO;
		  
	}

}
