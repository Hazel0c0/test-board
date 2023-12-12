package com.example.test.domain.tag.service;

import com.example.test.domain.tag.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostTagService {
    private final PostTagRepository postTagRepository;
    public boolean delete(Long tagNo) {
        try {
            postTagRepository.deleteById(tagNo);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다.");
            return false;
        }
    }
}
