package com.cos.blog.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("failed to update");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);
		/*
		 * @Transactional 어노테이션이 있으면 수정 내용을 자동으로 commit해줌.
		 * 이것을 더티체킹이라고 한다
		 */
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> showUserList(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 받아보자
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		/* 만약 user/4 를 찾을때 DB에서 찾아오지 못하게 되면 user가 null이 됨
		 * 그럼 return 할때 null이 return이 됨.. 프로그램에 문제가 생김
		 * Optional로 User객체를 감싸서 가져올 테니 null인지 아닌지 판단해서 return해라! 라는것임
		 * 여기서도 userRepository 를 받아올때 Optional임
		 */
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. ID = "+id);
		});
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
//			}
//		});

		return user;
	}
	// http://localhost:8000/blog/dummy/join (request)
	// http의 body에 username, password, email 데이터를 가지고 (request)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id = " + user.getId());
		System.out.println("username = " + user.getUsername());
		System.out.println("password = " + user.getPassword());
		System.out.println("email = " + user.getEmail());
		System.out.println("role = " + user.getRole());
		System.out.println("createDate = " + user.getCreateDate());
		user.setRole(RoleType.USER);
		try {
			userRepository.save(user);
		}catch (Exception e) {
			return "중복된 아이디 혹은 이메일입니다";
		}
		return "회원가입이 완료되었습니다.";
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 없는 유저 번호입니다.";
		}
		return "삭제되었습니다.";
	}
}
