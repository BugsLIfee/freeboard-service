package com.erbf.bugsLife.freeboard.service.logic;

import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import com.erbf.bugsLife.freeboard.domain.FreeboardSubComm;
import com.erbf.bugsLife.freeboard.repository.FreeboardCommentRepo;
import com.erbf.bugsLife.freeboard.repository.FreeboardSubCommRepo;
import com.erbf.bugsLife.freeboard.service.FreeboardCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FreeboardCommentServiceImpl implements FreeboardCommentService {

    @Autowired
    FreeboardCommentRepo repo;

    @Autowired
    FreeboardSubCommRepo subRepo;


    @Override
    public List<FreeboardComment> getComments(Long postId) throws NoSuchElementException {
        return repo.findByPostId(postId);
    }

    @Override
    public void createComment(FreeboardComment comment) {
       repo.save(comment);
    }

    @Override
    public FreeboardComment getComment(Long commentId) {
        return repo.findById(commentId).get();
    }

    @Override
    public List<FreeboardComment> getCommentList() {
        return repo.findAll();
    }

    @Override
    public void deleteComment(Long commentId) {
        repo.deleteById(commentId);
    }

    @Override
    public void createSubComment(FreeboardSubComm subComm) {
        subRepo.save(subComm);
    }

    @Override
    public void deleteSubcomment(Long subCommId) {
        subRepo.deleteById(subCommId);
    }

    @Override
    public FreeboardSubComm getSubComment(Long subCommId) {
        return subRepo.findById(subCommId).get();
    }
}
