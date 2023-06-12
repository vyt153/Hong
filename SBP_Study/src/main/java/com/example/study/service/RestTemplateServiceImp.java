package com.example.study.service;

import java.net.URI;

import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.study.dto.MemberDTO;
import org.slf4j.Logger;

@Service
public class RestTemplateServiceImp implements RestTemplateService {

	private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImp.class);
	
	@Override
	public String getStudy() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:90")
				.path("/study")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : ", responseEntity.getStatusCodeValue());
		LOGGER.info("body : {}", responseEntity.getBody());
		return responseEntity.getBody();
	}

	@Override
	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:90")
				.path("/name")
				.queryParam("name", "Hong")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : ", responseEntity.getStatusCodeValue());
		LOGGER.info("body : {}", responseEntity.getBody());
		return responseEntity.getBody();	
		}

	@Override
	public String getName2() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:90")
				.path("/path-variable/{name}")
				.encode()
				.build()
				.expand("Hong")
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		LOGGER.info("status code : ", responseEntity.getStatusCodeValue());
		LOGGER.info("body : {}", responseEntity.getBody());
		return responseEntity.getBody();	
	}

	@Override
	public ResponseEntity<MemberDTO> postDto() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:90")
				.path("/member")
				.queryParam("name", "Hong")
				.queryParam("email", "b@b.com")
				.queryParam("organization", "Ezen")
				.encode()
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("Hong!!");
		memberDTO.setEmail("a@a.com");
		memberDTO.setOrganization("Ezen!!");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO,
				MemberDTO.class);
		
		LOGGER.info("status code : ", responseEntity.getStatusCodeValue());
		LOGGER.info("body : {}", responseEntity.getBody());
		return responseEntity;
	}


	@Override
	public ResponseEntity<MemberDTO> addHeader() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:90")
				.path("/add-header")
				.encode()
				.build()
				.toUri();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName("Hong");
		memberDTO.setEmail("b@b.com");
		memberDTO.setOrganization("Ezen");
		
		RequestEntity<MemberDTO> requestEntity = RequestEntity.post(uri)
				.header("study-header", "Hong's Study")
				.body(memberDTO);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(
				requestEntity, MemberDTO.class);
		LOGGER.info("status code : ", responseEntity.getStatusCodeValue());
		LOGGER.info("body : {}", responseEntity.getBody());
		return responseEntity;
	}

}
