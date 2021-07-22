package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert //insert 할때 null인 값은 쿼리문에 실행되지 않게 해줌.
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100) //비밀번호는 암호화 된 데이터를 넣어야하기 때문에 length를 넉넉히 잡는다
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//DB는 RoleType이라는게 없다
	@Enumerated(EnumType.STRING)
	private RoleType role; // String < Enum ( admin, user, manager 등 열거형이기 때문에 ) 여기선 그냥 String으로  
	
	@CreationTimestamp // 시간이 자동으로 입력이 됨
	private Timestamp createDate;

}
