package com.example.test.domain.board.controller;

import com.example.test.domain.board.controller.dto.request.createRequestDTO;
import com.example.test.domain.board.controller.dto.request.UpdateRequestDTO;
import com.example.test.domain.board.controller.dto.response.PostResponseDTO;
import com.example.test.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    String COMMON_LOG = "api/board Controller : ";

    /**
     * 게시글 작성
     * @param postRequestDTO : 게시판 코드, 제목, 내용, 작성자
     * @return message
     */
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Validated @RequestBody final createRequestDTO postRequestDTO) {
        log.debug(COMMON_LOG+"create post dto - {}", postRequestDTO);

        PostResponseDTO postResponseDTO = boardService.insertPost(postRequestDTO);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PutMapping("/{postNo}")
    public ResponseEntity<PostResponseDTO> updatePost(
        @PathVariable final Long postNo,
        @Validated @RequestBody final UpdateRequestDTO updatedPostDto) {
        log.debug(COMMON_LOG+"update post number - {} / dto - {}", postNo,updatedPostDto);

        PostResponseDTO updateResponseDTO = boardService.update(postNo, updatedPostDto);
        return ResponseEntity.ok(updateResponseDTO);
    }


}
