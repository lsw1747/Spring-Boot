package com.cos.blog.model;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
               fetch = FetchType.EAGER,
               cascade = CascadeType.REMOVE) // mappedBy : 연관관계의 주인이 아니다(난 FK가 아니에요~ DB에 column을 만들지 마세요) 라는 의미
    // Reply 테이블에는 board, user정보가 같이 있기 때문에 board -> reply -> board -> reply ...의 과정이 반복될 수 있다.
    // 무한 참조 방지를 위해, board가 호출될 때는, 해당 어노테이션이 가리키는 테이블을 호출하지 않도록 함.
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> reply; //하나의 게시글에 여러개의 댓글이 달려있을 수 있음. one-to-many / One : Many = Board : Reply

    @CreationTimestamp
    private Timestamp createDate;
}
