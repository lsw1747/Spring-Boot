package com.cos.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
 * 인증이 안된 사용자는 /auth**로 접속하도록 허용 일반 / 주소일땐 index.jsp로 허용 static 이하에 있는 폴더 허용
 */


@Controller
public class UserController
{

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
}
