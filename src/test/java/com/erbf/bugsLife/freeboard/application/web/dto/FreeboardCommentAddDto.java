package com.erbf.bugsLife.freeboard.application.web.dto;

import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class FreeboardCommentAddDto {
    private Long id;
    private Long postId;
    private String writer;
    private String content;
    private String registerDate;
    private Boolean isHide;
    private int reportCnt;
    private String pwd;

    public FreeboardCommentAddDto(FreeboardComment comment){
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.registerDate = comment.getRegisterDate();
        this.isHide = comment.getIsHide();
        this.reportCnt = comment.getReportCnt();
    }

    public FreeboardComment toEntity(){
        PasswordEncoding passwordEncoding = new PasswordEncoding();
        String encodePwd  = passwordEncoding.encode(this.pwd);

        return FreeboardComment.builder()
                .id(this.id)
                .postId(this.postId)
                .content(this.content)
                .writer(this.writer)
                .registerDate(this.registerDate)
                .pwd(encodePwd)
                .isHide(this.isHide)
                .reportCnt(this.reportCnt)
                .build();
    }
}
