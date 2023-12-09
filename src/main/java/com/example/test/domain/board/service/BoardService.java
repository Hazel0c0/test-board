package com.example.test.domain.board.service;

import com.example.test.domain.board.controller.dto.request.PostRequestDTO;
import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.repository.BoardDefRepository;
import com.example.test.domain.board.repository.PostRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
@Getter
public class BoardService {
    private final BoardDefRepository boardDefRepository;
    private final PostRepository postRepository;

    String COMMON_LOG = "api/board Service : ";

    public String insertPost(PostRequestDTO dto){
        Post post = postRepository.save(dtoToEntity(dto));
        log.info(COMMON_LOG + "saved post dto - {}", post);
            postRepository.save(post);
            return "Success";
    }

    private Post dtoToEntity(PostRequestDTO dto) {
        BoardDef byBoardCd = boardDefRepository.findByBoardCd(dto.getBoardCd());

        return Post.builder()
                .boardCd(byBoardCd)
                .postSj(dto.getPostSj())
                .postCn(dto.getPostCn())
                .regstrId(dto.getRegstrId())
                .build();
    }
}
