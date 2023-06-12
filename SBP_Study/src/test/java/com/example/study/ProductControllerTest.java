package com.example.study;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.study.dto.ProductDto;
import com.example.study.service.ProductServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProductServiceImp productService;

	@Test
	@DisplayName("Product 데이터 가져오기 테스트")
	void getProductTest() throws Exception {
		
		// given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
		given(productService.getProduct("123")).willReturn(
				new ProductDto("158", "pen",5000,2000));
		
		String productId = "123";
		
		//andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
		mockMvc.perform(
				get("/product/"+productId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId").exists())
		.andExpect(jsonPath("$.productName").exists())
		.andExpect(jsonPath("$.productPrice").exists())
		.andExpect(jsonPath("$.productStock").exists())
		.andDo(print());
		
		verify(productService).getProduct("123");
	}
	
	@Test
	@DisplayName("Product 데이터 생성 테스트")
	void createProductTest() throws Exception {
		
		given(productService.saveProduct("123123", "pen", 5000, 2000)).willReturn(
				new ProductDto("123123", "pen", 5000, 2000));
		
		ProductDto productDto = ProductDto.builder()
				.productId("123123").productName("pen")
				.productPrice(5000).productStock(2000).build();
		String json = new ObjectMapper().writeValueAsString(productDto);
		
		mockMvc.perform(
				post("/product")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.productId").exists())
		.andExpect(jsonPath("$.productName").exists())
		.andExpect(jsonPath("$.productPrice").exists())
		.andExpect(jsonPath("$.productStock").exists())
		.andDo(print());
		
		verify(productService).saveProduct("123123", "pen", 5000, 2000);
	}
}
