package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


// 이 어노테이션이 있어야 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌.
@Service
public class UserService
{
    private static final String USER_INFO_REQUEST_URI = "https://kapi.kakao.com/v2/user/me";

    private static final String LOGIN_URI = "https://kauth.kakao.com/oauth/token";

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

    private static final String CALLBACK_URI = "http://localhost:8000/auth/kakao/callBack";

    private static final String AUTHORIZATION_CODE = "authorization_code";

    private static final String REST_API_KEY = KEY.getString("API_KEY");

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Transactional
    public void join(User user)
    {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


    public List<User> findAll()
    {
        return userRepository.findAll();
    }


    @Transactional(readOnly = true)
    public User findById(String username)
    {
        // UserRepository.java에서 findByUsername의 값을 Optional<User> 에 담았기 때문에 이것을 부를때는 orElse와 같이 예외처리를 해주어야 한다. 
        // 1. orElse() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값을 반환함. 
        // 2. orElseGet() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환함. 
        // 3. orElseThrow() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.

        return userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });
    }


    @Transactional
    public void update(User user)
    {
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("사용자를 찾을 수 없습니다. : " + user.getId());
        });

        if(persistance.getOauth() == null || persistance.getOauth() == "") {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }
    }


//    @Transactional
    public void kakaoCallBack(String code)
    {
        //POST 방식으로 key-value 데이터를 카카오에 요청해야함
        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성,카카오에서 요구하는 content type인 x-www-form-urlencoded로 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", CONTENT_TYPE);

        //HttpBody 오브젝트 생성, 카카오에서 요구하는 4가지 파라미터들을 추가
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", AUTHORIZATION_CODE);
        params.add("client_id", REST_API_KEY);
        params.add("redirect_uri", CALLBACK_URI);
        params.add("code", code);

        //Header와 Body를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        //Http요청해서 ResponseEntity에 담기
        ResponseEntity<String> response = rt.exchange(LOGIN_URI, HttpMethod.POST, kakaoTokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        }
        catch(JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //POST 방식으로 key-value 데이터를 카카오에 요청해야함
        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트 생성,카카오에서 요구하는 content type인 x-www-form-urlencoded로 세팅
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", CONTENT_TYPE);

        //Header와 Body를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        //Http요청해서 ResponseEntity에 담기
        ResponseEntity<String> response2 = rt2.exchange(USER_INFO_REQUEST_URI, HttpMethod.POST, kakaoProfileRequest, String.class);

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        //가입된 유저인지 체크
        User kakaoUser = User.builder().username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId()).password(cosKey).email(kakaoProfile.getKakao_account().getEmail()).oauth("kakao").build();
        User originUser = findById(kakaoUser.getUsername());

        //가입자가 아니라면
        if(originUser.getUsername() == null) {
            join(kakaoUser);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
