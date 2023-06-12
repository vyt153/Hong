package com.example.study.service;

import com.example.study.dto.ProductDto;

public interface ProductService {
	ProductDto getProduct(String productId);
	ProductDto saveProduct(String productId, String productName,
							int productPrice,int productStock);
}
