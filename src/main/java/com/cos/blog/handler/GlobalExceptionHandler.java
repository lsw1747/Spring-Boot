package com.cos.blog.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler
{

    @ExceptionHandler(value = Exception.class) //모든 에러는 이 컨트롤러로 매핑
    public ResponseDto<String> HandleArgumentException(Exception e)
    {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
