package com.mamathadigital.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mamathadigital.entities.Category;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotCreatedException;
import com.mamathadigital.repositories.CategoryRepository;

@Component
public class CategoryDAOImpl implements ICategoryDAO 
{
	
	private CategoryRepository categoryRepository;
	
	

	public CategoryDAOImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}



	@Override
	public Optional<Category> insertCategory(Category category) 
	{
		Category existingCategory = this.categoryRepository.findByCategoryName(category.getCategoryName());
		
		if(existingCategory==null)
		{
			Category savedCategory = this.categoryRepository.save(category);
			return savedCategory!=null ? Optional.of(savedCategory) : Optional.empty();
		}
		else
		{
			throw new CategoryNotCreatedException("Category already available with name : "+category.getCategoryName());
		}
		
		
		
	}



	@Override
	public Optional<List<Category>> getAllCategory() 
	{
		long count = this.categoryRepository.count();
		if(count>0)
		{
			List<Category> categories = this.categoryRepository.findAll();
			return Optional.of(categories);
		}
		else 
		{
			return Optional.empty();
		}
		
	}



	@Override
	public Optional<String> updateCategory(String categoryId, String categoryName) 
	{
		boolean existCategory = this.categoryRepository.existsById(categoryId);
		if(existCategory)
		{
			Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);
			Category category = optionalCategory.get();
			category.setCategoryName(categoryName);
			this.categoryRepository.save(category);
			return Optional.of("Category updated successfully with id : "+categoryId);
		}
		else
		{
			return Optional.empty();
		}
		
	}



	@Override
	public Optional<Category> getCategoryByCategoryId(String categoryId) 
	{
		if(this.categoryRepository.existsById(categoryId))
		{
			return  this.categoryRepository.findById(categoryId);
		}
		return Optional.empty();
	}

}
