package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//이 어노테이션이 있어야 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void join(User user) {
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

