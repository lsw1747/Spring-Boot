package com.cos.blog.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        System.out.println("call security config");
        http.csrf().disable().authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm");
    }
}
