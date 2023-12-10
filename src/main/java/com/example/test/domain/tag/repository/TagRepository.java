package com.example.test.domain.tag.repository;

import com.example.test.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
}