package com.devlucas.commerce.dto;

import com.devlucas.commerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "nome requerido")
	private String name;
	
	@Size(min = 10, message = "descricao precisa ter no minimo 10 caracteres")
	@NotBlank(message = "nome requerido")
	private String description;
	
	@Positive(message = "o preco deve ser positivo")
	private double price;
	private String imgUrl;
	
	public ProductDto() {
	}

	public ProductDto(Long id, String name, String description, double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDto(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}
