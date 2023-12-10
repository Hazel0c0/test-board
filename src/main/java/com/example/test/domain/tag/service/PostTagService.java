package com.example.test.domain.tag.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.repository.PostRepository;
import com.example.test.domain.board.service.BoardDefService;
import com.example.test.domain.tag.controller.dto.request.CreateTagRequestDTO;
import com.example.test.domain.tag.controller.dto.response.PostTagResponseDTO;
import com.example.test.domain.board.model.PostTag;
import com.example.test.domain.board.model.Tag;
import com.example.test.domain.tag.repository.PostTagRepository;
import com.example.test.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostTagService {
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    String COMMON_LOG = "api/tag Service : ";

    public PostTagResponseDTO insertTag(CreateTagRequestDTO createDTO) {
        PostTag saved = postTagRepository.save(dtoToEntity(createDTO));
        log.info(COMMON_LOG + "saved postTag dto - {}", createDTO);

        return PostTagResponseDTO.from(saved);
    }

    private PostTag dtoToEntity(CreateTagRequestDTO dto) {
        Post post = postRepository.findById(dto.getPostNo()).orElseThrow(() -> new NoSuchElementException("게시글"));
        Tag tag = tagRepository.findById(dto.getTagNo()).orElseThrow(() -> new NoSuchElementException("태그"));
        BoardDef boardDef = BoardDefService.boardDefFrom(dto.getBoardCd());
        return PostTag.builder()
            .postNo(post)
            .boardCd(boardDef)
            .tagNo(tag)
            .build();
    }

    public boolean delete(Long boardTagId) {
        try {
            postRepository.deleteById(boardTagId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다.");
            return false;
        }
    }
}
