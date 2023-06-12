package com.example.study.service;

import org.springframework.http.ResponseEntity;

import com.example.study.dto.MemberDTO;

public interface RestTemplateService {
	
	public String getStudy();
	
	public String getName();
	
	public String getName2();
	
	public ResponseEntity<MemberDTO> postDto();
	
	public ResponseEntity<MemberDTO> addHeader();
	
}
