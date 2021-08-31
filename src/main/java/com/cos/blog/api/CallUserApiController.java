package com.cos.blog.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class CallUserApiController
{
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/callApi",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public List<User> callUserApi()
    {
        List<User> userList = userService.findAll();
        return userList;
    }


    @RequestMapping(value = "/callApi/{username}",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public User callUserApiByUsername(@PathVariable(value = "username") String username)
    {
        User user = userService.findById(username);
        return user;
    }

}
