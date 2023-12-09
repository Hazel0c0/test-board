package com.example.test.domain.board.controller.dto.request;

import com.example.test.domain.board.model.BoardDef;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostRequestDTO {
    private String boardCd;

    private String postSj;

    private String postCn;

    private String regstrId;
}
