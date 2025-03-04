package com.mamathadigital.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.mamathadigital.util.idgenerators.CategoryIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("deprecation")
@Entity
@Table(name="CATEGORY_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category 
{
	
	@Id
    @GenericGenerator(name="category_id_generator", type = CategoryIdGenerator.class)
    @GeneratedValue(generator = "category_id_generator")
    @Column(name="CATEGORY_ID")
	private String categoryId;
	
    
    @Column(name="CATEGORY_NAME")
	private String categoryName;
    
	
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category",orphanRemoval = true)
	private List<Product> products=new ArrayList<>();	
    
    //add product helper method
    public void addProduct(Product product)
    {
    	product.setCategory(this);
    	products.add(product);
    }
    
    //remove product helper method
    public void removeProduct(Product product)
    {
    	products.remove(product);
    	product.setCategory(null);
    }
	

}
