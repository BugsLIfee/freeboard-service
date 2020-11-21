package com.erbf.bugsLife.freeboard.application.web.dto;
import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import com.erbf.bugsLife.freeboard.domain.FreeboardSubComm;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FreeboardSubCommDto {
    private Long id;
    private Long commentId;
    private String content;
    private String registerDate;
    private Boolean isHide;
    private int reportCnt;
    private String pwd;
    private String writer;

    public FreeboardSubCommDto(FreeboardSubComm subComment){
        this.id = subComment.getId();
        this.commentId = subComment.getComment().getId();
        this.content = subComment.getContent();
        this.registerDate = subComment.getRegisterDate();
        this.isHide = subComment.getIsHide();
        this.writer=subComment.getWriter();
        this.reportCnt = subComment.getReportCnt();
    }


    public FreeboardSubComm toEntity(FreeboardComment comment){
        PasswordEncoding passwordEncoding = new PasswordEncoding();
        String encodePwd  = passwordEncoding.encode(this.pwd);


        return FreeboardSubComm.builder()
                .id(this.id)
                .comment(comment)
                .content(this.content)
                .registerDate(this.registerDate)
                .isHide(this.isHide)
                .reportCnt(this.reportCnt)
                .writer(this.writer)
                .pwd(encodePwd)
                .build();


    }

}
