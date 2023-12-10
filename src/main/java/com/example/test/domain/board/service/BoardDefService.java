package com.example.test.domain.board.service;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.repository.BoardDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BoardDefService {
    private static BoardDefRepository boardDefRepository;

    @Autowired
    public BoardDefService(BoardDefRepository boardDefRepository) {
        BoardDefService.boardDefRepository = boardDefRepository;
    }


    public static BoardDef boardDefFrom(String boardCode){
        return boardDefRepository.findById(boardCode).orElseThrow(NoSuchElementException::new);
    }
}
