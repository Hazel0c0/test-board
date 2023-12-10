package com.example.test.domain.board.controller;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.service.BoardDefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board-def")
@Slf4j
public class BoardDefController {
    private final BoardDefService boardDefService;
    String COMMON_LOG = "api/board-tag Controller : ";

    /**
     * board def create
     * @param requestBody : board code, name
     * @return : code
     */
    @PostMapping
    public ResponseEntity<?> createBoardDef(@Validated @RequestBody final Map<String, String> requestBody) {
        String boardCd = requestBody.get("boardCd");
        String boardNm = requestBody.get("boardNm");

        log.info(COMMON_LOG + "createBoardDef dto - {} , {}", boardCd, boardNm);

        String boardCode = boardDefService.insertBoardDef(boardCd, boardNm).getBoardCd();

        return ResponseEntity.ok("board code : "+boardCode);
    }

    @DeleteMapping("/{boardCd}")
    public ResponseEntity<Boolean> deleteBoardDef(@PathVariable final String boardCd) {
        log.info(COMMON_LOG + "delete BoardDef code - {}", boardCd);

        boolean isDeleted = boardDefService.delete(boardCd);
        return ResponseEntity.ok(isDeleted);
    }

}
