package com.example.study.handler;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.study.dao.ProductDAO;
import com.example.study.entity.ProductEntity;

@Service
@Transactional
public class ProductDataHandlerImp implements ProductDataHandler{
	
	ProductDAO productDAO;
	
	@Autowired
	public ProductDataHandlerImp(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
		ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productStock);
		
		return productDAO.saveProduct(productEntity);
	}

	@Override
	public ProductEntity getProductEntity(String productId) {
		return productDAO.getProduct(productId);
	}

}
