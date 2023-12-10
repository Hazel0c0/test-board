package com.example.test.domain.board.service;

import com.example.test.domain.board.controller.dto.request.createPostRequestDTO;
import com.example.test.domain.board.controller.dto.request.UpdatePostRequestDTO;
import com.example.test.domain.board.controller.dto.response.PostResponseDTO;
import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.repository.BoardDefRepository;
import com.example.test.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;

    String COMMON_LOG = "api/board Service : ";

    // 저장
    public PostResponseDTO insertPost(createPostRequestDTO postRequestDTO) {
        Post post = postRepository.save(dtoToEntity(postRequestDTO));
        log.info(COMMON_LOG + "saved post dto - {}", post);

        return PostResponseDTO.from(post);
    }

    private Post dtoToEntity(createPostRequestDTO dto) {
        BoardDef byBoardCd = boardDefRepository.findById(dto.getBoardCd()).orElseThrow();

        return Post.builder()
            .boardCd(byBoardCd)
            .postSj(dto.getPostSj())
            .postCn(dto.getPostCn())
            .regstrId(dto.getRegstrId())
            .build();
    }

    // 수정
    public PostResponseDTO update(Long postNo, UpdatePostRequestDTO updatedPostDto) {
        Post foundPost = returnPost(postNo);
        setPost(foundPost,updatedPostDto);

        return PostResponseDTO.from(foundPost);
    }
    private void setPost(Post p, UpdatePostRequestDTO dto){
        p.setPostSj(dto.getPostSj());
        p.setPostCn(dto.getPostCn());

        log.info(COMMON_LOG + "updated post dto - {}", p);
    }

    // 삭제
    public boolean delete(Long postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다.");
            return false;
        }
    }

    // 전체 조회
    public List<PostResponseDTO> getListAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
            .map(PostResponseDTO::from)
            .collect(Collectors.toList());
    }

    // 단일 조회
    public PostResponseDTO getPost(Long postId) {
        return PostResponseDTO.from(returnPost(postId));
    }

    public Post returnPost(Long postNo) {
        return postRepository.findById(postNo)
            .orElseThrow(() -> new NoSuchElementException("게시물"));
    }
}
