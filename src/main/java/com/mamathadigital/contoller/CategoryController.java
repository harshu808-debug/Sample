package com.mamathadigital.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamathadigital.dtos.CategoryRequest;
import com.mamathadigital.dtos.CategoryResponse;
import com.mamathadigital.dtos.Message;
import com.mamathadigital.services.categoryservice.ICategoryService;

@RestController
@RequestMapping("/admin")
public class CategoryController 
{
	private ICategoryService categoryService;
	
	
	
	public CategoryController(ICategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}



	@PostMapping(value="/addCategory")
     public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest)
     {
    	 return    new ResponseEntity<CategoryResponse> (this.categoryService.addCategoryWithProducts(categoryRequest),HttpStatus.OK);
     }
	
	@GetMapping(value="/allCategories")
	public ResponseEntity<List<CategoryResponse>> getAllCategory()
	{
		return new ResponseEntity<List<CategoryResponse>>(this.categoryService.getAllCategory(),HttpStatus.OK);
	}
	
	
	
	@GetMapping(value="/{categoryId}/{categoryName}")
	public ResponseEntity<Message> updateCategoryName(@PathVariable String categoryId,
			                                          @PathVariable String categoryName
			                                         )
	{
		//System.out.println(categoryId+" "+categoryName);
		String msg = this.categoryService.updateCategory(categoryId, categoryName.trim());
		return new ResponseEntity<Message>(new Message(msg),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryResponse> getCategoryByCategoryId(@PathVariable String categoryId)
	{
		//System.out.println(categoryId);
		CategoryResponse categoryResponse = this.categoryService.findCategoryByCategoryId(categoryId);
	    return new ResponseEntity<CategoryResponse>(categoryResponse,HttpStatus.OK);
	
	}
	
	
}
