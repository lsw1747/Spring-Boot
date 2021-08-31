package com.cos.blog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private PrincipalDetailService principalDetailService;


    @Bean
    public BCryptPasswordEncoder encodePWD()
    {
        return new BCryptPasswordEncoder();
    }

    /*
     * Spring Security가 대신 로그인할때 암호화된 password가 어떤 hash 타입으로 회원가입되었는지 알아야 같은 해쉬로 암호화해 DB에 있는 해쉬와 비교할 수 있다.
     */


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable() //테스트시에만 csrf 비활성화
            .authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**", "/callApi/**").permitAll() //해당 주소는 전부 권한x
            .anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm") //로그인은 /auth/loginForm 으로
            .loginProcessingUrl("/auth/loginProc")//spring security가 해당 주소로 오는 요청을 가로채서 대신 로그인함.
            .defaultSuccessUrl("/");
    }
}
