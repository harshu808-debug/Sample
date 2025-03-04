package com.mamathadigital.services.categoryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mamathadigital.dao.ICategoryDAO;
import com.mamathadigital.dtos.CategoryRequest;
import com.mamathadigital.dtos.CategoryResponse;
import com.mamathadigital.dtos.ProductResponseDTO;
import com.mamathadigital.entities.Category;
import com.mamathadigital.entities.Product;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotCreatedException;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotFoundException;
import com.mamathadigital.mappers.EntityToDTOMapper;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	private ICategoryDAO categoryDAO;
	
	

	public CategoryServiceImpl(ICategoryDAO categoryDAO) {
		super();
		this.categoryDAO = categoryDAO;
	}



	@Override
	public CategoryResponse addCategoryWithProducts(CategoryRequest categoryRequest) 
	{
		Category category = new Category();
		category.setCategoryName(categoryRequest.getCategoryName());
		
		if(categoryRequest.getProducts()!=null)
		{
		List<Product> products = categoryRequest.getProducts().stream().map(productRequest ->
			new Product(productRequest.getProductName(),
					    productRequest.getProductColor(), 
					    productRequest.getProductImage(), 
					    productRequest.getProductPrice(),
					    productRequest.getProductDiscount(), 
					    productRequest.getProductLength(),
					    productRequest.getProductWidth(),
					    productRequest.getProductKeyFeatures(),
					    productRequest.getProductDescription())
		).collect(Collectors.toList());
		
		products.stream().forEach(product -> category.addProduct(product));
		
		Optional<Category> insertedCategory = this.categoryDAO.insertCategory(category);
		
		if(insertedCategory.isPresent())
		{
			CategoryResponse categoryResponse = EntityToDTOMapper.toCategoryResponse(category);
			return categoryResponse;
		}
		else
		{
			throw new CategoryNotCreatedException("Category not created !");
		}
		
		}
		else 
		{
			Optional<Category> insertedCategory = this.categoryDAO.insertCategory(category);
			if(insertedCategory.isPresent())
			{
				CategoryResponse categoryResponse = EntityToDTOMapper.toCategoryResponse(category);
				return categoryResponse;
			}
			else
			{
				throw new CategoryNotCreatedException("Category not created !");
			}
		}
		
		
	}



	@Override
	public List<CategoryResponse> getAllCategory() 
	{
		Optional<List<Category>> allCategory = this.categoryDAO.getAllCategory();
		if(allCategory.isPresent())
		{
			List<CategoryResponse> allCategoryResponses=new ArrayList<>();
			List<Category> categories = allCategory.get();
			
			for(Category category : categories)
			{
				List<Product> products = category.getProducts();
				List<ProductResponseDTO> productResponses= new ArrayList<>();
				for(Product product : products)
				{
					ProductResponseDTO productResponseDTO = EntityToDTOMapper.toProductResponseDTO(product);
					productResponses.add(productResponseDTO);
				}
				
				CategoryResponse categoryResponse = EntityToDTOMapper.toCategoryResponse(category);
			    categoryResponse.setProducts(productResponses);
			    
			    allCategoryResponses.add(categoryResponse);
			}
			
			return allCategoryResponses;
			
		}
		else
		{
			throw new CategoryNotFoundException("No Category available");
		}
	}



	@Override
	public String updateCategory(String categoryId, String categoryName) 
	{
		//System.out.println(categoryId + " "+categoryName.length());
		if(categoryId==null)
		{
			throw new RuntimeException("CategoryId must not be null");
		}
		else if(categoryName==null ||  categoryName.length() < 5)
		{
			throw new RuntimeException("CategoryName must not be null and it should be a valid categoryName");
		}
		else
		{
			Optional<String> updateCategory = this.categoryDAO.updateCategory(categoryId, categoryName);
		    
			return updateCategory.orElseThrow(()->new CategoryNotFoundException("Category not exist with id : "+categoryId));
		
		
		}
	}



	@Override
	public CategoryResponse findCategoryByCategoryId(String categoryId) 
	{
		if(categoryId !=null)
		{
		Optional<Category> optionalCategory = this.categoryDAO.getCategoryByCategoryId(categoryId);
	    if(optionalCategory.isPresent())
	    {
	    	Category category = optionalCategory.get();
	    	CategoryResponse categoryResponse = EntityToDTOMapper.toCategoryResponse(category);
	        return categoryResponse;
	    }
	    else 
	    {
			  throw new CategoryNotFoundException("Category not exist with id : "+categoryId);
	    }
		}
		else 
		{
			throw new RuntimeException("Category must not be null");
		}
	
	
	}

}
