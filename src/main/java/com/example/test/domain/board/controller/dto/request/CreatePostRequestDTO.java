package com.example.test.domain.board.controller.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreatePostRequestDTO {
    private String boardCd;

    private String postSj;

    private String postCn;

    private String regstrId;
}
