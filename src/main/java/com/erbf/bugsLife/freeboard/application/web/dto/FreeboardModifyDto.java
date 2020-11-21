package com.erbf.bugsLife.freeboard.application.web.dto;

import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FreeboardModifyDto {
    private Long id;
    private String cate;
    private String title;
    private String content;
    private String updateDate;
    private String pwd;


    public FreeboardPost toEntity(FreeboardPost newPost){
        PasswordEncoding passwordEncoding = new PasswordEncoding();
        String encodePwd  = passwordEncoding.encode(this.pwd);

        return FreeboardPost.builder()
                .id(this.id)
                .cate(this.cate)
                .title(this.title)
                .content(this.content)
                .registerDate(newPost.getRegisterDate())
                .updateDate(this.updateDate)
                .pwd(encodePwd)
                .isHide(newPost.getIsHide())
                .viewCnt(newPost.getViewCnt())
                .reportCnt(newPost.getReportCnt())
                .likes(newPost.getLikes())
                .build();
    }
}
