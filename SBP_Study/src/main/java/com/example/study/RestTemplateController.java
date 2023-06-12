package com.example.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.dto.MemberDTO;
import com.example.study.service.RestTemplateService;

@RestController
public class RestTemplateController {
	@Autowired
	RestTemplateService restTemplateService;
	
	@GetMapping(value = "/study")
	public String getStudy() {
		return restTemplateService.getStudy();
	}
	
	@GetMapping(value = "/name")
	public String getName() {
		return restTemplateService.getName();
	}
	
	@GetMapping(value = "/name2")
	public String getName2() {
		return restTemplateService.getName2();
	}
	
	@PostMapping(value = "/add-header")
	public ResponseEntity<MemberDTO> addHeader(){
		return restTemplateService.addHeader();
	}
	
	@PostMapping(value = "/dto")
	public ResponseEntity<MemberDTO> postDto(){
		return restTemplateService.postDto();
	}
	
}
