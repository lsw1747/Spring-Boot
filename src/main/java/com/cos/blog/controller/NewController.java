package com.cos.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {
	
	@GetMapping("/http")
	public String getTest() {
		return "get 성공";
	}
	
	@PostMapping("/http/")
	public String postTest() {
		return "post 성공";
	}
	
}
