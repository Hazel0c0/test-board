package com.example.test.domain.board.controller.dto.request;

import lombok.*;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateRequestDTO {
    private String postSj;

    private String postCn;
}
