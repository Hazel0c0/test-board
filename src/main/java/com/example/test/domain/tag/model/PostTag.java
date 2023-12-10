package com.example.test.domain.tag.model;

import javax.persistence.*;

import com.example.test.domain.board.model.BoardDef;
import com.example.test.domain.board.model.Post;
import lombok.*;

@Entity
@Table(name = "POST_TAG")
@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_TAG_ID")
    private Long boardTagId; // 게시물 태그ID

    @ManyToOne
    @JoinColumn(name = "POST_NO")
    @Setter
    private Post postNo; // 글번호

    @ManyToOne
    @JoinColumn(name = "BOARD_CD")
    private BoardDef boardCd; // 게시판(분류)코드

    @ManyToOne
    @JoinColumn(name = "TAG_NO")
    private Tag tagNo; // 태그 ID

}
