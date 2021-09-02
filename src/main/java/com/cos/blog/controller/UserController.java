package com.cos.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.UserService;


@Controller
public class UserController
{

    @Autowired
    private UserService userService;


    @GetMapping("/auth/signupForm")
    public String signupForm()
    {
        return "user/signupForm";
    }


    @GetMapping("/auth/loginForm")
    public String loginForm()
    {
        return "user/loginForm";
    }


    @GetMapping("/auth/updateForm")
    public String update()
    {
        return "user/userInfo";
    }


    @GetMapping("/auth/kakao/callBack")
    public String callKakaoApi(String code)
    {
        userService.kakaoCallBack(code);
        return "redirect:/";
    }

}
