package com.example.test.domain.tag.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.service.BoardDefService;
import com.example.test.domain.board.model.Tag;
import com.example.test.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {
    private final TagRepository tagRepository;
    String COMMON_LOG = "api/tag Service : ";

    public Tag insertTag(String tagName, String boardCd) {
        BoardDef boardDefOj = BoardDefService.boardDefFrom(boardCd);

        Tag tag = Tag.builder()
                .tag(tagName)
                .boardCd(boardDefOj)
                .build();

        tagRepository.save(tag);

        log.info(COMMON_LOG+"saved Tag - {}", tag);

        return tag;
    }

    public boolean delete(Long tagNo) {
        try {
            tagRepository.deleteById(tagNo);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다.");
            return false;
        }
    }
}
