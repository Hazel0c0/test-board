package com.example.test.domain.board.controller.dto.response;

import com.example.test.domain.board.model.Post;
import com.example.test.domain.board.model.PostTag;
import com.example.test.domain.board.model.Tag;
import com.example.test.domain.tag.repository.PostTagRepository;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostResponseDTO {
    private Long postNo;
    private String boardCd;
    private String postSj;
    private String postCn;
    private String regstrId;
    private String regDt;
    private List<String> tagName;

    public static PostResponseDTO from(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Tag> tags = post.getPostTags().stream().map(pt -> pt.getTagNo()).toList();

        return PostResponseDTO.builder()
                .postNo(post.getPostNo())
                .boardCd(post.getBoardCd().getBoardCd())
                .postSj(post.getPostSj())
                .postCn(post.getPostCn())
                .regstrId(post.getRegstrId())
                .regDt(post.getRegDt().format(formatter))
                .tagName(tags.stream().map(t -> t.getTag()).collect(Collectors.toList()))
                .build();
    }
}