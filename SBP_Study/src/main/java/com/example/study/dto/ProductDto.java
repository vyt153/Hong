package com.example.study.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	@NotNull
	//@Size(min = 8,max = 8)  길이나 패턴, 규칙 지정 ex. abcdefgh
	private String productId;
	
	@NotNull
	private String productName;
	
	@NotNull
	@Min(value = 500)
	@Max(value = 3000000)
	private int productPrice;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 9999)
	private int productStock;
	
}
