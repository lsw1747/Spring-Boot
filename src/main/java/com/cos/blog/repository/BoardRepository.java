package com.cos.blog.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>
{
    Optional<Board> findById(int id);

    @Modifying
    @Transactional
    void deleteById(int id);

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int updateCount(int id);
}
