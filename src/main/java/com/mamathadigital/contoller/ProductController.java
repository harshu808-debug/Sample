package com.mamathadigital.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mamathadigital.dtos.ProductRequestDTO;
import com.mamathadigital.dtos.ProductResponseDTO;
import com.mamathadigital.services.productservice.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	private IProductService productService;
	
	
	
	public ProductController(IProductService productService) {
		super();
		this.productService = productService;
	}

	
	@PostMapping(value = "/addProduct")
	public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO)
	{
		ProductResponseDTO productResponseDTO = this.productService.storeProduct(productRequestDTO);
		return new ResponseEntity<ProductResponseDTO>(productResponseDTO,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<ProductResponseDTO>>  getAllProducts()
	{
		return new ResponseEntity<List<ProductResponseDTO>>(this.productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductResponseDTO> findProductByProductId(@PathVariable String productId)
	{
		return new ResponseEntity<ProductResponseDTO>(this.productService.getProductByProductId(productId),HttpStatus.FOUND);
	}
	
	@GetMapping(value="/bycategory/{categoryId}")
	public ResponseEntity<List<ProductResponseDTO>> getProductsByCategoryId(@PathVariable String categoryId)
	{
		return new ResponseEntity<List<ProductResponseDTO>>(this.productService.getProductsByCategoryId(categoryId),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProductByProductId(@PathVariable String productId)
	{
		return new ResponseEntity<String>(this.productService.deleteProductByProductId(productId),HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateProduct/{productId}")
	public ResponseEntity<ProductResponseDTO> updateProduct(
			@RequestBody ProductRequestDTO productRequestDTO,
			@PathVariable String productId)
	{
		return new ResponseEntity<ProductResponseDTO>(this.productService.updateProduct(productRequestDTO, productId),HttpStatus.OK);
	}
	
	
	
}
