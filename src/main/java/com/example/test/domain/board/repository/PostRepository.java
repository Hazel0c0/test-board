package com.example.test.domain.board.repository;

import com.example.test.domain.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
}
