package com.erbf.bugsLife.freeboard.application.web.dto;
import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FreeboardCommentDto {

    private Long id;
    private Long postId;
    private String writer;
    private String content;
    private String registerDate;
    private Boolean isHide;
    private int reportCnt;
    private List<FreeboardSubCommDto> subComments;

    public FreeboardCommentDto(FreeboardComment comment){
        this.id=comment.getId();
        this.postId = comment.getPostId();
//        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.registerDate =comment.getRegisterDate();
        this.isHide = comment.getIsHide();
        this.reportCnt = comment.getReportCnt();
        this.subComments = comment.getSubComments().stream().map(subComment -> new FreeboardSubCommDto(subComment)).collect(Collectors.toList());
    }

    public FreeboardComment toEntity(){
        return FreeboardComment.builder()
                .id(this.id)
                .content(this.content)
                .registerDate(this.registerDate)
                .postId(this.postId)
                .writer(this.writer)
//                .post(post)
                .isHide(this.isHide)
                .reportCnt(this.reportCnt)
                .build();
    }
}
