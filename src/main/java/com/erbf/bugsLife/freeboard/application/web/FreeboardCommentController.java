package com.erbf.bugsLife.freeboard.application.web;

import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardCommentAddDto;
import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardCommentDto;
import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardSubCommDto;
import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import com.erbf.bugsLife.freeboard.service.FreeboardCommentService;
import com.erbf.bugsLife.freeboard.service.FreeboardPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/freeboard")
public class FreeboardCommentController {

    @Autowired
    FreeboardPostService postService;

    @Autowired
    FreeboardCommentService commService;



    //댓글작성
    @PostMapping("/{postId}/comment")
    public FreeboardCommentAddDto addComment(@PathVariable Long postId, @RequestBody FreeboardCommentAddDto commentDto ){
//        FreeboardPost post = postService.getPost(postId);
//        FreeboardComment newComment = commentDto.toEntity(post);
        FreeboardComment newComment = commentDto.toEntity();
        commService.createComment(newComment);
        return commentDto;
    }

    //댓글 삭제
    @DeleteMapping("/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestParam String pwd){
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        if (passwordEncoding.matches(pwd, commService.getComment(commentId).getPwd()))
            commService.deleteComment(commentId);
        else
            return "댓 삭제 에러";
        return "Freeboard 댓글 삭제 완료";
    }


    //대댓글 작성
    @PostMapping("/{postId}/comment/{commentId}/subcomment")
    public FreeboardSubCommDto addSubComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody FreeboardSubCommDto subCommDto){
        FreeboardComment comment = commService.getComment(commentId);
        commService.createSubComment(subCommDto.toEntity(comment));
        return subCommDto;
    }

    //대댓글 삭제
    @DeleteMapping("/{postId}/comment/{commentId}/{subcommId}")
    public String deleteSubComment(@PathVariable Long postId, @PathVariable Long commentId, @PathVariable Long subcommId ,@RequestParam String pwd){
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        if (passwordEncoding.matches(pwd, commService.getSubComment(subcommId).getPwd()))
            commService.deleteSubcomment(subcommId);
        else
            return "대댓글 삭제 에러";
        return "Freeboard 대댓글 삭제 완료";
    }

//    @DeleteMapping("/{postId}/comment/{commentId}/subcomment")
//    public String deleteSubComment

    @GetMapping("/{postId}/comment")
    public List<FreeboardCommentDto> getComments(@PathVariable Long postId){
        List<FreeboardComment> comments = commService.getComments(postId);
        return comments.stream().map(comment -> new FreeboardCommentDto(comment)).collect(Collectors.toList());
    }

//    @GetMapping("/comment")
//    public List<FreeboardCommentDto> getCommentList(){
//        List<FreeboardComment> comments = commService.getCommentList();
//        return comments.stream().map(comment -> new FreeboardCommentDto(comment)).collect(Collectors.toList());
//    }



}
