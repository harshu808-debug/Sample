package com.mamathadigital.entities;

import org.hibernate.annotations.GenericGenerator;

import com.mamathadigital.util.idgenerators.ProductIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("deprecation")
@Entity
@Table(name="PRODUCT_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	
	@Id
    @GenericGenerator(name="product_id_generator", type = ProductIdGenerator.class)
    @GeneratedValue(generator = "product_id_generator")
	@Column(name="PRODUCT_ID")
    private String productId;
    
    @Column(name="PRODUCT_NAME")
    private String productName;
    
    @Column(name="PRODUCT_COLOR")
    private String productColor;
    
    @Column(name="PRODUCT_IMAGE")
    private String productImage;
    
    @Column(name="PRODUCT_PRICE")
    private Double productPrice;
    
    @Column(name="PRODUCT_DISCOUNT")
    private Double productDiscount;
    
    @Column(name="PRODUCT_LENGTH")
    private Integer productLength;
    
    @Column(name="PRODUCT_WIDTH")
    private Integer productWidth;
    
    @Column(name="KEY_FEATURES")
    private String productKeyFeatures;
    
    @Column(name="DESCRIPTION")
    private String productDescription;
    
    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

	public Product(String productName, String productColor, String productImage, Double productPrice,
			Double productDiscount, Integer productLength, Integer productWidth, String productKeyFeatures,
			String productDescription) {
		super();
		this.productName = productName;
		this.productColor = productColor;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productLength = productLength;
		this.productWidth = productWidth;
		this.productKeyFeatures = productKeyFeatures;
		this.productDescription = productDescription;
	}
    
    
    

}
