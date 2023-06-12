package com.example.study;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {
	
	@DeleteMapping(value = "delete/{variable}")
	public String DeleteVariable(@PathVariable String variable) {
		return variable;
	}
}
