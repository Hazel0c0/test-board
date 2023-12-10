package com.example.test.domain.tag.controller;

import com.example.test.domain.tag.controller.dto.request.createTagRequestDTO;
import com.example.test.domain.tag.controller.dto.response.PostTagResponseDTO;
import com.example.test.domain.tag.service.PostTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post-tag")
@Slf4j
public class PostTagController {
    private final PostTagService postTagService;

    String COMMON_LOG = "api/post-tag Controller : ";

    @PostMapping
    public ResponseEntity<?> createPostTag(@Validated @RequestBody final createTagRequestDTO createDTO) {
        log.info(COMMON_LOG + "create post dto - {}", createDTO);

        PostTagResponseDTO postTagResponseDTO = postTagService.insertTag(createDTO);
        return ResponseEntity.ok(postTagResponseDTO);
    }
}
