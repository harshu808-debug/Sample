package com.mamathadigital.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse 
{
    private String categoryId;
    private String categoryName;
    private List<ProductResponseDTO> products;
}
