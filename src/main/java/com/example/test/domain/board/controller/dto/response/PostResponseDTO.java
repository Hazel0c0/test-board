package com.example.test.domain.board.controller.dto.response;

import com.example.test.domain.board.model.Post;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
    private Long postNo;
    private String boardCd;
    private String postSj;
    private String postCn;
    private String regstrId;
    private String regDt;

    public static PostResponseDTO from(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return PostResponseDTO.builder()
            .postNo(post.getPostNo())
            .boardCd(post.getBoardCd().getBoardCd())
            .postSj(post.getPostSj())
            .postCn(post.getPostCn())
            .regstrId(post.getRegstrId())
            .regDt(post.getRegDt().format(formatter))
            .build();
    }
}