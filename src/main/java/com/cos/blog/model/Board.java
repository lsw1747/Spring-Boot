package com.cos.blog.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Board
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,
            length = 100)
    private String title;

    @Lob //대용량 데이터를 사용할때,
    private String content;

    private int count; // 조회수

    /*
     * DB는 오브젝트를 저장할 수 없다. (Foreign key) 하지만 자바는 오브젝트를 저장 가능. 실제 DB에서는 userId라는 값으로 들어갈 것이다.
     */
    @ManyToOne(fetch = FetchType.EAGER) //Many : One = Board : User 1명의 유저가 여러 게시글을 쓸수 있는 N:1관계
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board",
               fetch = FetchType.EAGER) // mappedBy : 연관관계의 주인이 아니다(난 FK가 아니에요~ DB에 column을 만들지 마세요) 라는 의미
    //@JoinColumn(name="replyId") 필요없음, 만들어지면안됨
    private List<Reply> reply; //하나의 게시글에 여러개의 댓글이 달려있을 수 있음.

    @CreationTimestamp
    private Timestamp createDate;
}
