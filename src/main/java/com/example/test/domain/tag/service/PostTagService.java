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
    private final PostRepository postRepository;

    String COMMON_LOG = "api/tag Service : ";

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
