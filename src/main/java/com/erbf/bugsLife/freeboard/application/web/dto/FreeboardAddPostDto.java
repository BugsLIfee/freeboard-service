package com.erbf.bugsLife.freeboard.application.web.dto;

import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class FreeboardAddPostDto {
    private String cate;
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private Boolean isHide;
    private int viewCnt;
    private int reportCnt;
//    private int likes;
    private String pwd;


    public FreeboardAddPostDto(FreeboardPost post){
        this.cate = post.getCate();
        this.title = post.getTitle();
        this.content =post.getContent();
        this.registerDate = post.getRegisterDate();
        this.updateDate = post.getUpdateDate();
        this.isHide = post.getIsHide();
        this.viewCnt = post.getViewCnt();
        this.reportCnt = post.getReportCnt();
//        this.likes = post.getLikes();
    }

   public FreeboardPost toEntity(){
       PasswordEncoding passwordEncoding = new PasswordEncoding();
       String encodePwd  = passwordEncoding.encode(this.pwd);

        return FreeboardPost.builder()
                .cate(this.cate)
                .title(this.title)
                .content(this.content)
                .registerDate(this.registerDate)
                .updateDate(this.updateDate)
                .pwd(encodePwd)
                .isHide(this.isHide)
                .viewCnt(this.viewCnt)
                .reportCnt(this.reportCnt)
                .build();
    }

}
