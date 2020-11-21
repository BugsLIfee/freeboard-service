package com.erbf.bugsLife.freeboard.service;

import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import com.erbf.bugsLife.freeboard.domain.FreeboardSubComm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeboardCommentService {
    public List<FreeboardComment> getComments(Long postId);
    public void createComment(FreeboardComment comment);
    public FreeboardComment getComment(Long commentId);
    public List<FreeboardComment> getCommentList();

    public void deleteComment(Long commentId);

    //subComment
    public void createSubComment(FreeboardSubComm subComm);
    public void deleteSubcomment(Long subCommId);
    public FreeboardSubComm getSubComment(Long subCommId);
}
