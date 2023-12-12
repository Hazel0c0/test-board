package com.example.test.domain.tag.repository;

import com.example.test.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {

    // tag entity에 일치하는 'tag' 몇 개인지
    int countByTag(String tag);
    Optional<Tag> findByTag(String tag);
}