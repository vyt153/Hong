package com.example.study;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.dto.ProductDto;
import com.example.study.exception.ProductException;
import com.example.study.exception.Constants.ExceptionClass;
import com.example.study.service.ProductService;

import javax.validation.Valid;

import org.slf4j.Logger;

@RestController
public class ProductController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productservice) {
		this.productService = productservice;
	}
	
	@GetMapping(value = "product/{productId}")
	public ProductDto getProduct(@PathVariable String productId) {
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("[ProductController] perform {} of SBP Study API","getProduct");
		ProductDto productDto = productService.getProduct(productId);
		
		LOGGER.info("[ProductController] Response :: ProductId = {}, ProductName = {}, ProductPrice = {}, ProductStock = {}, Response Time = {}ms", 
				productDto.getProductId(),productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(),(System.currentTimeMillis())- startTime);
		return productService.getProduct(productId);
	}
	
	@PostMapping(value = "/product")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
		
		// Validation Code Example
		if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {
			LOGGER.error("[createProduct] failed Response :: productId is Empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
		}
		
		String productId = productDto.getProductId();
		String productName = productDto.getProductName();
		int productPrice = productDto.getProductPrice();
		int productStock = productDto.getProductStock();
		
		ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);
		LOGGER.info(
				"[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
				response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(value = "/product/{productId}")
	public ProductDto deleteProduct(@PathVariable String productId) {
		return null;
	}
	
	@PostMapping(value = "/product/exception")
	public void exceptionTest() throws ProductException {
		throw new ProductException(ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러가 발생하였습니다.");
	}
}
