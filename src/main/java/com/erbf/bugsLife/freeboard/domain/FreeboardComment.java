package com.erbf.bugsLife.freeboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class FreeboardComment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private Long postId;
    private String writer;
    private String content;
    private String registerDate;
    private Boolean isHide;


    @Column(nullable = false)
    private String pwd;

    @Column(columnDefinition = "integer default 0")
    private int reportCnt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="post_id", nullable = false)
//    FreeboardPost post;

    @Builder.Default
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FreeboardSubComm> subComments = new ArrayList<FreeboardSubComm>();

}
