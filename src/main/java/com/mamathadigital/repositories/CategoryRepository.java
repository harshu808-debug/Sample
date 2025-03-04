package com.mamathadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mamathadigital.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String>
{
    Category findByCategoryName(String categoryName);
}
