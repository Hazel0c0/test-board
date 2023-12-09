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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    String COMMON_LOG = "api/board Controller : ";

    /**
     * 게시글 작성
     *
     * @param postRequestDTO : 게시판 코드, 제목, 내용, 작성자
     */
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Validated @RequestBody final createRequestDTO postRequestDTO) {
        log.info(COMMON_LOG + "create post dto - {}", postRequestDTO);

        PostResponseDTO postResponseDTO = boardService.insertPost(postRequestDTO);
        return ResponseEntity.ok(postResponseDTO);
    }

    /**
     * 게시글 수정
     *
     * @param postNo         : 게시글 번호
     * @param updatedPostDto : 제목, 내용
     */
    @PutMapping("/{postNo}")
    public ResponseEntity<PostResponseDTO> updatePost(
        @PathVariable final Long postNo,
        @Validated @RequestBody final UpdateRequestDTO updatedPostDto) {
        log.info(COMMON_LOG + "update post number - {} / dto - {}", postNo, updatedPostDto);

        PostResponseDTO updateResponseDTO = boardService.update(postNo, updatedPostDto);
        return ResponseEntity.ok(updateResponseDTO);
    }

    /**
     * 게시글 삭제
     *
     * @param postNo 게시글 번호
     * @return 삭제 유무
     */
    @DeleteMapping("/{postNo}")
    public ResponseEntity<Boolean> deletePost(@PathVariable final Long postNo) {
        log.info(COMMON_LOG + "delete post number - {}", postNo);

        boolean isDeleted = boardService.delete(postNo);
        return ResponseEntity.ok(isDeleted);
    }

    // 게시판 전체글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPostList() {
        log.debug(COMMON_LOG + "post find ALL");
        List<PostResponseDTO> allPostDto = boardService.getListAll();
        return ResponseEntity.ok(allPostDto);
    }

    // 게시글 단일 조회(details page) - by id
    @GetMapping("/{postNo}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable final Long postNo) {
        log.info(COMMON_LOG + "find post by id - {} ", postNo);

        PostResponseDTO postDto = boardService.getPost(postNo);
        return ResponseEntity.ok(postDto);
    }
}
