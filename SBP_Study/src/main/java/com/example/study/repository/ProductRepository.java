package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String>{
	
}
