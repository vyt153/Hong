package com.example.study.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.entity.ProductEntity;
import com.example.study.repository.ProductRepository;

@Service
public class ProductDAOImp implements ProductDAO {
	
	ProductRepository productRepository;
	
	@Autowired
	public ProductDAOImp(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductEntity saveProduct(ProductEntity productEntity) {
		productRepository.save(productEntity);
		return productEntity;
	}

	@Override
	public ProductEntity getProduct(String productId) {
		@SuppressWarnings("deprecation")
		ProductEntity productEntity = productRepository.getById(productId);
		return productEntity;
	}

}
