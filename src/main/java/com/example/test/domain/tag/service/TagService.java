package com.example.test.domain.tag.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.service.BoardDefService;
import com.example.test.domain.board.service.BoardService;
import com.example.test.domain.tag.controller.dto.response.PostTagResponseDTO;
import com.example.test.domain.tag.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final BoardService boardService;

    public PostTagResponseDTO insertTag(String tagName, String boardCd) {
        BoardDef boardDef = BoardDefService.boardDefFrom(boardCd);

        Tag.builder()
            .tag(tagName)
            .boardCd(boardDef)
            .build();

        return null;
    }
}
