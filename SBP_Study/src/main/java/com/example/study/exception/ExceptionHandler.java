package com.example.study.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

@RestControllerAdvice
public class ExceptionHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
		HttpHeaders responseHeaders = new HttpHeaders();
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		LOGGER.info("Advice 내 ExceptionHandler 호출");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생");
		
		return new ResponseEntity<>(map,responseHeaders,httpStatus);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value =ProductException.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(ProductException e){
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error type", e.getHttpStatusType());
		map.put("error code", Integer.toString(e.getHttpStatusCode()));
		map.put("message", e.getMessage());
		
		return new ResponseEntity<>(map, responseHeaders,e.getHttpStatus());
	}
}
