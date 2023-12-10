package com.example.test.domain.tag.controller.dto.request;

import lombok.Getter;
import lombok.ToString;
@ToString
@Getter
public class CreateTagRequestDTO {
    private String boardCd; // 게시판(분류)코드
    private Long postNo; // 글번호
    private Long tagNo; // 태그 ID
}
