package com.mamathadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mamathadigital.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String>
{

}
