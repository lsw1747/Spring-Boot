package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}




//User findByUsernameAndPassword(String username,String password);
////둘이 같은 내용
//@Query(value="SELECT * FROM user WHERE username =?1 AND password =?2", nativeQuery = true)
//User login(String username, String password);