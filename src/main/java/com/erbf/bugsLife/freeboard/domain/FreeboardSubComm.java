package com.erbf.bugsLife.freeboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FreeboardSubComm {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private String registerDate;
    private Boolean isHide;
    private String writer;

    @Column(nullable = false)
    private String pwd;


    @Column(columnDefinition = "integer default 0")
    private int reportCnt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne
    @JoinColumn(name="comment_id")
    private FreeboardComment comment;
}
