package com.example.test.domain.tag.controller;

import com.example.test.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        String tag = requestBody.get("tag");
        String boardCd = requestBody.get("boardCd");

        log.info(COMMON_LOG + "create tag dto - {} , {}", tag, boardCd);

        String tagName = tagService.insertTag(tag, boardCd).getTag();

        return ResponseEntity.ok(tagName+" tag save success");
    }

    @DeleteMapping("/{tagNo}")
    public ResponseEntity<Boolean> deleteTag(@PathVariable final Long tagNo) {
        log.info(COMMON_LOG + "delete post number - {}", tagNo);

        boolean isDeleted = tagService.delete(tagNo);
        return ResponseEntity.ok(isDeleted);
    }
}
