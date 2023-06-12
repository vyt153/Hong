package com.example.study;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.dto.MemberDTO;

@RestController
public class PutController {
	
	@PutMapping(value = "/default")
	public String putMtd() {
		return "Hello World";
	}
	
	@PutMapping(value = "/member")
	public String putMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();
		
		putData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	
	@PutMapping(value = "/member1")
	public String putMemberDTO1(@RequestBody MemberDTO memberDTO) {
		return memberDTO.toString();
	}
	
	@PutMapping(value = "/member2")
	public MemberDTO putMemberDTO2(@RequestBody MemberDTO memberDTO) {
		return memberDTO;
	}
	
	@PutMapping(value = "/member3")
	public ResponseEntity<MemberDTO> putMemberDTO3(@RequestBody MemberDTO memberDTO) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
	}
	
}
