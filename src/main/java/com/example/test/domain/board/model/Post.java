package com.example.test.domain.board.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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

    @Column(name = "POST_SJ")
    private String postSj; // 제목

    @Column(name = "POST_CN", length = 1000)
    private String postCn; // 내용

    @Column(name = "REGSTR_ID")
    private String regstrId; // 작성자

    @CreatedDate
    @Column(name = "REG_DT", updatable = true)
    private LocalDateTime regDt; // 작성일시
}