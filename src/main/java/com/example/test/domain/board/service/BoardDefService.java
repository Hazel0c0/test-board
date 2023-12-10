package com.example.test.domain.board.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.repository.BoardDefRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class BoardDefService {
    private static BoardDefRepository boardDefRepository;
    String COMMON_LOG = "api/board-def Service : ";

    @Autowired
    public BoardDefService(BoardDefRepository boardDefRepository) {
        BoardDefService.boardDefRepository = boardDefRepository;
    }

    /**
     * Common method
     * @param boardCode : String type board code
     * @return : Board Def Object
     */
    public static BoardDef boardDefFrom(String boardCode) {
        return boardDefRepository.findById(boardCode).orElseThrow(NoSuchElementException::new);
    }

    public BoardDef insertBoardDef(String boardCd, String boardNm) {
        BoardDef savedDef = BoardDef.builder()
                .boardCd(boardCd)
                .boardNm(boardNm)
                .build();

        boardDefRepository.save(savedDef);

        log.info(COMMON_LOG + "insert BoardDef - {} ", savedDef);

        return savedDef;
    }

    public boolean delete(String boardCd) {
        try {
            boardDefRepository.deleteById(boardCd);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("삭제에 실패했습니다. \n"+e.getMessage());
            return false;
        }

    }
}
