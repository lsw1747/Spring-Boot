package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class) //IllegalArgumentExcpetion에 대한 모든 에러는 이 컨트롤러로 매핑
	public String HandleArgumentException(IllegalArgumentException e) {
		
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
