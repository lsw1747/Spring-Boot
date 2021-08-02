package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// 이 어노테이션이 있어야 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌.
@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void join(User user)
    {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}

//	@Transactional
//	public int join(User user) {
//		try{
//			userRepository.save(user);
//			return 1;
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : join"+e.getMessage());
//			return -1;
//		}
//	}
