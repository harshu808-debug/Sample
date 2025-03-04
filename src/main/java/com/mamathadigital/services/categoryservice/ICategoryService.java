package com.mamathadigital.services.categoryservice;

import java.util.List;

import com.mamathadigital.dtos.CategoryRequest;
import com.mamathadigital.dtos.CategoryResponse;

public interface ICategoryService 
{
     CategoryResponse addCategoryWithProducts(CategoryRequest categoryRequest);
     List<CategoryResponse> getAllCategory();
     String updateCategory(String categoryId,String categoryName);
     CategoryResponse findCategoryByCategoryId(String categoryId);
}
