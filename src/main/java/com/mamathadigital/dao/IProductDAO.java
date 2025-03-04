package com.mamathadigital.dao;

import java.util.List;
import java.util.Optional;

import com.mamathadigital.entities.Product;

public interface IProductDAO {
	
	 Optional<Product>  addProduct(Product product,String categoryId);
	 Optional<List<Product>> getAllProduct();
	 Optional<Product> getSingleProductByProductId(String productId);
	 Optional<List<Product>> getProductsByCategoryId(String categoryId);
	 Optional<String> deleteProductByProductId(String productId);
	 Optional<Product> updateProduct(Product product);

}
