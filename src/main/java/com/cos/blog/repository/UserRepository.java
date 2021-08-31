package com.cos.blog.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String Username);
    List<User> findAll();
}

//User findByUsernameAndPassword(String username,String password);
////둘이 같은 내용
//@Query(value="SELECT * FROM user WHERE username =?1 AND password =?2", nativeQuery = true)
//User login(String username, String password);