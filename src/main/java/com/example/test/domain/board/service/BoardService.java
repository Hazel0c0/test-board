package com.example.test.domain.board.service;

import com.example.test.domain.board.controller.dto.request.createRequestDTO;
import com.example.test.domain.board.controller.dto.request.UpdateRequestDTO;
import com.example.test.domain.board.controller.dto.response.PostResponseDTO;
import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.repository.BoardDefRepository;
import com.example.test.domain.board.repository.PostRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Getter
public class BoardService {
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;

    String COMMON_LOG = "api/board Service : ";

    // 저장
    public PostResponseDTO insertPost(createRequestDTO postRequestDTO) {
        Post post = postRepository.save(dtoToEntity(postRequestDTO));
        log.info(COMMON_LOG + "saved post dto - {}", post);

        return PostResponseDTO.from(post);
    }

    private Post dtoToEntity(createRequestDTO dto) {
        BoardDef byBoardCd = boardDefRepository.findByBoardCd(dto.getBoardCd());

        return Post.builder()
            .boardCd(byBoardCd)
            .postSj(dto.getPostSj())
            .postCn(dto.getPostCn())
            .regstrId(dto.getRegstrId())
            .build();
    }

    // 수정
    public PostResponseDTO update(Long postNo, UpdateRequestDTO updatedPostDto) {
        Post foundPost = findById(postNo);
        setPost(foundPost,updatedPostDto);

        return PostResponseDTO.from(foundPost);
    }

    private void setPost(Post p, UpdateRequestDTO dto){
        p.setPostSj(dto.getPostSj());
        p.setPostCn(dto.getPostCn());

        log.info(COMMON_LOG + "updated post dto - {}", p);
    }

    private Post findById(Long postNo) {
        return postRepository.findById(postNo)
            .orElseThrow(NoSuchElementException::new);
    }
}
