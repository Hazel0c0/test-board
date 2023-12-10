package com.example.test.domain.tag.repository;

import com.example.test.domain.tag.model.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}