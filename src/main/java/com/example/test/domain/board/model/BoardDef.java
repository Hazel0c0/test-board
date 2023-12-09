package com.example.test.domain.board.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOARD_DEF")
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardDef {
    @Id
    @Column(name = "BOARD_CD")
    private String boardCd; // 게시판 코드 - MTO : POST class

    @Column(name = "BOARD_NM")
    private String boardNm;
}