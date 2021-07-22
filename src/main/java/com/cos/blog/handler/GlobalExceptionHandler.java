package com.cos.blog.handler;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
	
	public String HandleArgumentException(IllegalArgumentException e) {
		
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
