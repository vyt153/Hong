package com.example.study.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.study.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {
	@Id
	String productId;
	
	String productName;
	
	Integer productPrice;
	
	Integer productStock;
	
	public ProductDto toDto() {
		return ProductDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productStock(productStock)
				.build();
	}
}
