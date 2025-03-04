package com.mamathadigital.services.productservice;

import java.util.List;

import com.mamathadigital.dtos.ProductRequestDTO;
import com.mamathadigital.dtos.ProductResponseDTO;

public interface IProductService {
	
	ProductResponseDTO storeProduct(ProductRequestDTO productRequestDTO);
	List<ProductResponseDTO> getAllProducts();
	ProductResponseDTO getProductByProductId(String productId);
	List<ProductResponseDTO> getProductsByCategoryId(String categoryId);
	String deleteProductByProductId(String productId);
	ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO,String productid);

}
