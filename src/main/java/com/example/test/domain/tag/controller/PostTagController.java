package com.example.test.domain.tag.controller;

import com.example.test.domain.tag.service.PostTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post-tag")
@Slf4j
public class PostTagController {
    private final PostTagService postTagService;

    String COMMON_LOG = "api/post-tag Controller : ";


    @DeleteMapping("/{boardTagId}")
    public ResponseEntity<Boolean> deletePostTag(@PathVariable final Long boardTagId) {
        log.debug(COMMON_LOG + "delete post Tag number - {}", boardTagId);

        boolean isDeleted = postTagService.delete(boardTagId);
        return ResponseEntity.ok(isDeleted);
    }
}
