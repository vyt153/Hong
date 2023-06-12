package com.example.study.handler;

import com.example.study.entity.ProductEntity;

public interface ProductDataHandler {
	ProductEntity saveProductEntity(String productId, String productName,
							int productPrice, int productStock);
	
	ProductEntity getProductEntity(String productId);
}
