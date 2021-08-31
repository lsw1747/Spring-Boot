package com.cos.blog.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.cos.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>
{
    Optional<Board> findById(int id);

    @Modifying
    @Transactional
    void deleteById(int id);
}
