package com.example.test.domain.board.model;

import javax.persistence.*;

import com.example.test.domain.tag.model.PostTag;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"postTags"})
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO")
    private Long postNo;

    @ManyToOne
    @JoinColumn(name = "BOARD_CD")
    private BoardDef boardCd;

    @Setter
    @Column(name = "POST_SJ")
    private String postSj; // 제목

    @Setter
    @Column(name = "POST_CN", length = 1000)
    private String postCn; // 내용

    @Setter
    @Column(name = "REGSTR_ID")
    private String regstrId; // 작성자

    @CreatedDate
    @Column(name = "REG_DT", updatable = true)
    private LocalDateTime regDt; // 작성일시

    @Setter
    @OneToMany(mappedBy = "postNo", cascade = CascadeType.ALL)
    private List<PostTag> postTags = new ArrayList<>();

    public void addPostTag(PostTag postTag) {
        System.out.println("postTags = " + postTags);
        postTag.setPostNo(this);
        this.postTags.add(postTag);
    }

}