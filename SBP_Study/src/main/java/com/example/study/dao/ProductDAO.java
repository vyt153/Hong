package com.example.study.dao;

import com.example.study.entity.ProductEntity;

public interface ProductDAO {
	ProductEntity saveProduct(ProductEntity productEntity);
	
	ProductEntity getProduct(String productId);
}
