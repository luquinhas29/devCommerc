package com.devlucas.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devlucas.commerce.dto.ProductDto;
import com.devlucas.commerce.entities.Product;
import com.devlucas.commerce.repositories.ProductRepository;
import com.devlucas.commerce.service.exceptions.DataBaseException;
import com.devlucas.commerce.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Product product = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("recurso nao encontrado"));
		return new ProductDto(product);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDto> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDto(x));
	}
	
	@Transactional
	public ProductDto insert(ProductDto dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDto(entity);
	}
	
	@Transactional
	public ProductDto update(Long id, ProductDto dto) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDto(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado.");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado.");
		}
		catch(DataIntegrityViolationException e){
			throw new DataBaseException("falha de integridade referencial.");
		}
	}

	private void copyDtoToEntity(ProductDto dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
	}
}
