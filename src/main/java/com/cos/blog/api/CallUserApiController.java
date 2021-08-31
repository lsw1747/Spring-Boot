package com.cos.blog.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class CallUserApiController
{
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/callApi",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public List<User> callUserApi()
    {

        List<User> userList = userRepository.findAll();

        return userList;
    }


    @RequestMapping(value = "/callApi/{username}",
                    method = {RequestMethod.GET, RequestMethod.POST})
    public User callUserApiByUsername(@PathVariable(value = "username") String username)
    {
        /*
         * UserRepository.java에서 findByUsername의 값을 Optional<User> 에 담았기 때문에 이것을 부를때는 orElse와 같이 예외처리를 해주어야 한다. 1. orElse() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값을 반환함. 2. orElseGet() 메소드 : 저장된 값이 존재하면 그 값을
         * 반환하고, 값이 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환함. 3. orElseThrow() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.
         */
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
        });
        log.info("" + user);
        return user;
    }

}
