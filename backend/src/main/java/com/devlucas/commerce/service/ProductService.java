package com.devlucas.commerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devlucas.commerce.dto.ProductDto;
import com.devlucas.commerce.entities.Product;
import com.devlucas.commerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDto dto = new ProductDto(product);
		return dto;
		
		
	}

}
