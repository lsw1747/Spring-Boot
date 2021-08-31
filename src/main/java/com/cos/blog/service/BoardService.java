package com.cos.blog.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;


// 이 어노테이션이 있어야 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌.
@Service
public class BoardService
{

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void post(Board board, User user)
    {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }


    public Board detail(int id)
    {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 조회할 수 없습니다.");
        });
    }


    @Transactional
    public void delete(int id)
    {
        boardRepository.deleteById(id);
    }


    public Page<Board> getPostList(Pageable pageable)
    {
        return boardRepository.findAll(pageable);
    }
}