package com.example.test.domain.tag.controller;

import com.example.test.domain.tag.service.PostTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tag")
@Slf4j
public class PostTagController {
    private final PostTagService postTagService;
    String COMMON_LOG = "api/tag Controller : ";

    @DeleteMapping("/{tagNo}")
    public ResponseEntity<Boolean> deletePost(@PathVariable final Long tagNo) {
        log.info(COMMON_LOG + "delete post number - {}", tagNo);

        boolean isDeleted = postTagService.delete(tagNo);
        return ResponseEntity.ok(isDeleted);
    }
}
