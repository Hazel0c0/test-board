package com.example.test.domain.board.repository;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDefRepository extends JpaRepository<BoardDef, Long>{
    BoardDef findByBoardCd(String boardCd);

}
