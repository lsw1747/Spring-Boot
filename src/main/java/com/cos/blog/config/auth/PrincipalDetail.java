package com.cos.blog.config.auth;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;


/*
 * Spring Security가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를 Spring Security의 고유한 세션저장소에 저장을 해준다.
 */
@Getter
public class PrincipalDetail implements UserDetails
{
    private User user;


    public PrincipalDetail(User user) {
        this.user = user;
    }


    @Override
    public String getPassword()
    {
        return user.getPassword();
    }


    @Override
    public String getUsername()
    {
        return user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }


    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired()
    {

        return true;
    }


    @Override
    public boolean isEnabled()
    {

        return true;
    }


    // 계정의 권한을 return한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> {
            return "ROLE_" + user.getRole(); //ROLE_USER 형태로 return. Spring Security에서의 규칙 ROLE_XXX 이런식으로 나오도록 prefix를 붙여줘야함.
        });

        return collectors;
    }

}
