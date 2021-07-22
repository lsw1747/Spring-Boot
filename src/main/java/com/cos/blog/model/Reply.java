package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //하나의 게시글에 여러개의 댓글이 가능하도록 관계 설정
	@JoinColumn(name="boardId")
	private Board board;
	
	//하나의 유저는 여러개의 댓글을 달수 있다. 하나의 댓글을 여러 유저가 쓸 수 있나? -> X
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	
	@CreationTimestamp
	private Timestamp createData;
}
