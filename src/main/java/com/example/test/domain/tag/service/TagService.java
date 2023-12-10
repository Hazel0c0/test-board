package com.example.test.domain.tag.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.repository.BoardDefRepository;
import com.example.test.domain.board.repository.PostRepository;
import com.example.test.domain.tag.controller.dto.request.createTagRequestDTO;
import com.example.test.domain.tag.controller.dto.response.PostTagResponseDTO;
import com.example.test.domain.tag.model.PostTag;
import com.example.test.domain.tag.model.Tag;
import com.example.test.domain.tag.repository.PostTagRepository;
import com.example.test.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;
    private final BoardDefRepository boardDefRepository;

    String COMMON_LOG = "api/tag Service : ";

    public PostTagResponseDTO insertTag(createTagRequestDTO createDTO) {
        PostTag saved = postTagRepository.save(dtoToEntity(createDTO));
        log.info(COMMON_LOG + "saved post dto - {}", createDTO);

        return PostTagResponseDTO.from(saved);
    }

    private PostTag dtoToEntity(createTagRequestDTO dto) {
        Post post = postRepository.findById(dto.getPostNo()).orElseThrow(NoSuchElementException::new);
        Tag tag = tagRepository.findById(dto.getTagNo()).orElseThrow(NoSuchElementException::new);
        BoardDef boardDef = boardDefRepository.findById(dto.getBoardCd()).orElseThrow(NoSuchElementException::new);

        return PostTag.builder()
            .postNo(post)
            .boardCd(boardDef)
            .tagNo(tag)
            .build();
    }
}
