package com.example.test.domain.tag.controller;

import com.example.test.domain.tag.controller.dto.response.PostTagResponseDTO;
import com.example.test.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tag")
@Slf4j
public class TagController {
    private final TagService tagService;

    String COMMON_LOG = "api/tag Controller : ";

    @PostMapping
    public ResponseEntity<?> createTag(@Validated @RequestBody final Map<String, String> requestBody) {
        String tagName = requestBody.get("tag");
        String boardCd = requestBody.get("boardCd");

        log.info(COMMON_LOG + "create tag dto - {} , {}", tagName, boardCd);

//        PostTagResponseDTO postTagResponseDTO = tagService.insertTag(tagName, boardCd);
        return ResponseEntity.ok("");
    }
}
