package com.example.test.domain.board.controller;

import com.example.test.domain.board.controller.dto.request.PostRequestDTO;
import com.example.test.domain.board.controller.dto.response.PostResponseDTO;
import com.example.test.domain.board.service.BoardService;
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
@RequestMapping("api/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    String COMMON_LOG = "api/board Controller : ";

    /**
     * 게시글 작성
     * @param postRequestDTO :
     * @return message
     */
    @PostMapping
    public ResponseEntity<String> createPost(@Validated @RequestBody final PostRequestDTO postRequestDTO) {
        log.debug(COMMON_LOG+"create post dto - {}", postRequestDTO);

        String successMsg = boardService.insertPost(postRequestDTO);

        return ResponseEntity.ok(successMsg);
    }
}
