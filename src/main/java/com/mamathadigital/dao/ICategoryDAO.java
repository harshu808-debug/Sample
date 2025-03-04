package com.mamathadigital.dao;

import java.util.List;
import java.util.Optional;

import com.mamathadigital.entities.Category;

public interface ICategoryDAO {
	
	Optional<Category> insertCategory(Category category);
	Optional<List<Category>> getAllCategory();
	Optional<String> updateCategory(String categoryId,String categoryName);
	Optional<Category> getCategoryByCategoryId(String categoryId) ;

}
