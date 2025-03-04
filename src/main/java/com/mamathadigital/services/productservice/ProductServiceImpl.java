package com.mamathadigital.services.productservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mamathadigital.dao.IProductDAO;
import com.mamathadigital.dtos.ProductRequestDTO;
import com.mamathadigital.dtos.ProductResponseDTO;
import com.mamathadigital.entities.Product;
import com.mamathadigital.exceptions.productexceptions.ProductNotCreatedException;
import com.mamathadigital.exceptions.productexceptions.ProductNotFoundException;
import com.mamathadigital.mappers.EntityToDTOMapper;

@Service
public class ProductServiceImpl implements IProductService
{

	private IProductDAO productDAO;
	
	
	
	public ProductServiceImpl(IProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}

	@Override
	public ProductResponseDTO storeProduct(ProductRequestDTO productRequestDTO) 
	{
		if (productRequestDTO !=null) 
		{
			Product product = EntityToDTOMapper.toProduct(productRequestDTO);
			Optional<Product> productStored = this.productDAO.addProduct(product, productRequestDTO.getCategoryId());
		    return  EntityToDTOMapper
		    		.toProductResponseDTO(
		    				productStored.
		    				orElseThrow(()-> new ProductNotCreatedException("Product not created !")));
		
		
		}
		else 
		{
			 throw new RuntimeException("Invalid JSON !");
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() 
	{
		Optional<List<Product>> allProduct = this.productDAO.getAllProduct();
		if(allProduct.isPresent())
		{
			List<Product> listOfProducts = allProduct.get();
			List<ProductResponseDTO> listOfProductResponseDTOs = listOfProducts
					                                             .stream()
					                                             .map(productDAO -> EntityToDTOMapper
					                                            		 .toProductResponseDTO(productDAO))
					                                                     .collect(Collectors.toList());
			return listOfProductResponseDTOs;
		}
		else {
			throw new RuntimeException("No products available !");
		}
		
	}

	@Override
	public ProductResponseDTO getProductByProductId(String productId) 
	{
		if(productId !=null && !productId.isBlank() && !productId.equalsIgnoreCase("null"))
		{
			return EntityToDTOMapper
					.toProductResponseDTO(
							this.productDAO
							.getSingleProductByProductId(productId)
							.orElseThrow(()-> new ProductNotFoundException("Product not exist with id : "+productId)));
		}
		else {
			throw new RuntimeException("ProductId must not be null or blank !");
		}
	}

	@Override
	public List<ProductResponseDTO> getProductsByCategoryId(String categoryId) 
	{
		if(categoryId !=null && !categoryId.isBlank() && !categoryId.equalsIgnoreCase("null"))
		{
			Optional<List<Product>> productsByCategoryoId = this.productDAO.getProductsByCategoryId(categoryId);
			if(productsByCategoryoId.isPresent())
			{
				List<ProductResponseDTO> productResponseDTOs = productsByCategoryoId
						                                       .get()
						                                       .stream()
						                                       .map(product-> EntityToDTOMapper
						                                    		   .toProductResponseDTO(product))
						                                               .collect(Collectors.toList());
			    return productResponseDTOs;
			}
			else
			{
				throw new ProductNotFoundException("No product available for categoryid : "+categoryId);
			}
		}
		else 
		{
			throw new RuntimeException("CategoryId must not be null and blank !");
		}
	}

	@Override
	public String deleteProductByProductId(String productId) 
	{
		if(productId !=null && !productId.isBlank() && !productId.equalsIgnoreCase("null") )
		{
			Optional<String> deleteProductByProductId = this.productDAO.deleteProductByProductId(productId);
		    return deleteProductByProductId
		    		.orElseThrow(()->new ProductNotFoundException("Product not exist with id : "+productId));
		}
		else
		{
			throw new RuntimeException("ProductId must not be null or empty");
		}
	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO,String productId) 
	{
		if(productRequestDTO !=null)
		{
			if(productId!=null && !productId.isBlank())
			{
			Product product = EntityToDTOMapper.toProduct(productRequestDTO);
			product.setProductId(productId);
			Optional<Product> updateProduct = this.productDAO.updateProduct(product);
			return  EntityToDTOMapper.toProductResponseDTO(updateProduct.orElseThrow(()->new ProductNotCreatedException("Product not found with id : "+productId)));
			}
			else
			{
				throw new RuntimeException("Product id must not be null or empty");
			}
		}
		else
		{
			throw new RuntimeException("Something went wrong");
		}
	}
	
	

}
