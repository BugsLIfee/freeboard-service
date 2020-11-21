package com.erbf.bugsLife.freeboard.service;

import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FreeboardPostService {

    public List<FreeboardPost> getPosts();
    public void createPost(FreeboardPost post);
    public FreeboardPost getPost(Long postId);
    public void deletePost(Long postId);

}
