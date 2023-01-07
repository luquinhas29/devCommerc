package com.devlucas.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devlucas.commerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
