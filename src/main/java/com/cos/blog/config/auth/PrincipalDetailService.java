package com.cos.blog.config.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service
public class PrincipalDetailService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;


    /*
     * Spring Security가 로그인 요청을 가로챌때 username, password를 가로채는데 이때 password는 얘가 알아서 처리함 username이 DB에 있는지만 확인하면된다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
        });
        return new PrincipalDetail(principal); //Security의 session에 유저 정보가 저장이 됨.
    }
}
