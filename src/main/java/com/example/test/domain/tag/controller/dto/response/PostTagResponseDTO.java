package com.example.test.domain.tag.controller.dto.response;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import com.example.test.domain.tag.model.PostTag;
import com.example.test.domain.tag.model.Tag;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostTagResponseDTO {
    private Long boardTagId; // 게시물 태그ID

    private Long postNo; // 글번호

    private String boardCd; // 게시판(분류)코드

    private Long tagNo; // 태그 ID

    public static PostTagResponseDTO from(PostTag pTag){
        return PostTagResponseDTO.builder()
            .postNo(pTag.getPostNo().getPostNo())
            .boardCd(pTag.getBoardCd().getBoardCd())
            .tagNo(pTag.getTagNo().getTagNo())
            .build();
    }

}
