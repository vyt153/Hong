package com.example.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.dto.ProductDto;
import com.example.study.entity.ProductEntity;
import com.example.study.handler.ProductDataHandler;

@Service
public class ProductServiceImp implements ProductService{

	ProductDataHandler productDataHandler;
	
	@Autowired
	public ProductServiceImp(ProductDataHandler productDataHandler) {
		this.productDataHandler = productDataHandler;
	}
	
	@Override
	public ProductDto getProduct(String productId) {
		ProductEntity productEntity = productDataHandler.getProductEntity(productId);
		
		ProductDto productDto = new ProductDto(productEntity.getProductId(),
				productEntity.getProductName(),productEntity.getProductPrice(),
				productEntity.getProductStock());
		return productDto;
	}

	@Override
	public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
		ProductEntity productEntity = productDataHandler.saveProductEntity(productId,productName,productPrice,productStock);
		
		ProductDto productDto = new ProductDto(productEntity.getProductId(),
				productEntity.getProductName(),productEntity.getProductPrice(),
				productEntity.getProductStock());
		return productDto;
	}

}
