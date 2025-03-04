package com.mamathadigital.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mamathadigital.entities.Category;
import com.mamathadigital.entities.Product;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotFoundException;
import com.mamathadigital.repositories.CategoryRepository;
import com.mamathadigital.repositories.ProductRepository;

@Component
public class ProductDAOImpl implements IProductDAO 
{

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	
	

	public ProductDAOImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<Product> addProduct(Product product,String categoryId) 
	{
		
		boolean existsById = this.categoryRepository.existsById(categoryId);
		if(existsById)
		{
			Category category = this.categoryRepository.getReferenceById(categoryId);
			category.addProduct(product);
			product.setCategory(category);
			Product savedProduct = this.productRepository.save(product);
			return savedProduct!=null ? Optional.of(savedProduct) : Optional.empty();
		}
		else
		{
			throw new CategoryNotFoundException("Cateogory not found with id : "+categoryId);
		}
		
		
		
		
	}

	@Override
	public Optional<List<Product>> getAllProduct() 
	{
		List<Product> products = this.productRepository.findAll();
		if(products!=null && products.size() !=0)
		{
			return Optional.of(products);
		}
		else
		{
			return Optional.empty();
		}
	}

	@Override
	public Optional<Product> getSingleProductByProductId(String productId) 
	{
		boolean existsById = this.productRepository.existsById(productId);
		if(existsById)
		{
			Product product = this.productRepository.getReferenceById(productId);
			return Optional.of(product);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Product>> getProductsByCategoryId(String categoryId) 
	{
		if(this.categoryRepository.existsById(categoryId))
		{
			Category category = this.categoryRepository.getReferenceById(categoryId);
			List<Product> products = category.getProducts();
			if(products!=null && products.size() >0)
			{
				return Optional.of(products);
			}
			
		}
		return Optional.empty();
	}

	@Override
	public Optional<String> deleteProductByProductId(String productId) 
	{
		boolean existsById = this.productRepository.existsById(productId);
		if(existsById)
		{
			Product product = this.productRepository.getReferenceById(productId);
		    product.getCategory().removeProduct(product);
		    this.productRepository.delete(product);
		    return Optional.of("Product deleted successfully with id : "+productId);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Product> updateProduct(Product product) 
	{
		boolean existsById = this.productRepository.existsById(product.getProductId());
	    if(existsById)
	    {
	    	Product oldProduct = this.productRepository.getReferenceById(product.getProductId());
	        Category category = oldProduct.getCategory();
	        oldProduct.setProductColor(product.getProductColor());
	        oldProduct.setProductDescription(product.getProductDescription());
	        oldProduct.setProductKeyFeatures(product.getProductKeyFeatures());
	        oldProduct.setProductDiscount(product.getProductDiscount());
	        oldProduct.setProductImage(product.getProductImage());
	        oldProduct.setProductLength(product.getProductLength());
	        oldProduct.setProductWidth(product.getProductWidth());
	        oldProduct.setProductPrice(product.getProductPrice());
	        oldProduct.setProductName(product.getProductName());
	        Product updatedProduct = this.productRepository.save(oldProduct);
	        List<Product> products = category.getProducts();
	        boolean flag=false;
	        for(Product p : products)
	        {
	        	if(p.getProductId().equals(product.getProductId()))
	        	{
	        		p.setProductColor(product.getProductColor());
	        		p.setProductDescription(product.getProductDescription());
	        		p.setProductDiscount(product.getProductDiscount());
	        		p.setProductImage(product.getProductImage());
	        		p.setProductKeyFeatures(product.getProductKeyFeatures());
	        		p.setProductLength(product.getProductLength());
	        		p.setProductName(product.getProductName());
	        		p.setProductPrice(product.getProductPrice());
	        		p.setProductWidth(product.getProductWidth());
	        		
	        		System.out.println("Category updated with product id : "+product.getProductId());
	        		flag=true;
	        		break;
	        	}
	        }
	        if(flag && updatedProduct!=null)
	        {
	        	return Optional.of(updatedProduct);
	        }
	        else
	        {
	        	return Optional.empty();
	        }
	        
	    }
	    else 
	    {
			return Optional.empty();
		}
	
	
	}

}
