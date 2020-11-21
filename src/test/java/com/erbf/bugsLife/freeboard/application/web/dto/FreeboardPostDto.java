package com.erbf.bugsLife.freeboard.application.web.dto;


import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeboardPostDto {

    private Long id;
    private String cate;
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private Boolean isHide;
    private int viewCnt;
    private int reportCnt;
    private int likes;

//    private List<FreeboardCommentDto> comments = new ArrayList<FreeboardCommentDto>();

    public FreeboardPostDto(FreeboardPost freeboardPost) {
        this.id = freeboardPost.getId();
        this.cate = freeboardPost.getCate();
        this.title = freeboardPost.getTitle();
        this.content = freeboardPost.getContent();
        this.registerDate = freeboardPost.getRegisterDate();
        this.updateDate = freeboardPost.getUpdateDate();
        this.isHide = freeboardPost.getIsHide();
        this.viewCnt = freeboardPost.getViewCnt();
        this.reportCnt = freeboardPost.getReportCnt();
        this.likes = freeboardPost.getLikes();
//        this.comments = freeboardPost.getComments().stream().map(comment -> new FreeboardCommentDto(comment)).collect(Collectors.toList());
    }
//
//    public FreeboardPost toEntity(FreeboardPostDto post){
//        return FreeboardPost.builder()
//                .id(this.id)
//                .cate(this.cate)
//                .title(this.title)
//                .content(this.content)
//                .registerDate(this.registerDate)
//                .updateDate(this.updateDate)
//                .isHide(this.isHide)
//                .viewCnt(this.viewCnt)
//                .likes(this.likes)
//                .reportCnt(this.reportCnt)
//                .build();
//    }
}
