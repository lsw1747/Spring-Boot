package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.ReplyRepository;


@Service
public class ReplyService
{
    @Autowired
    ReplyRepository replyRepository;


    public void save(Reply reply, int boardId, User user)
    {
        Board board = new Board();
        board.setId(boardId);
        reply.setUser(user);
        reply.setBoard(board);

        replyRepository.save(reply);
    }


    public List<Reply> findAll()
    {
        return replyRepository.findAll();
    }
}