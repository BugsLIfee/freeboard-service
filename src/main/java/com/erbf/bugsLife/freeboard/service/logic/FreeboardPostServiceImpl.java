package com.erbf.bugsLife.freeboard.service.logic;

import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import com.erbf.bugsLife.freeboard.repository.FreeboardPostRepo;
import com.erbf.bugsLife.freeboard.service.FreeboardPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FreeboardPostServiceImpl implements FreeboardPostService {

    @Autowired
    FreeboardPostRepo repo;

    @Override
    public List<FreeboardPost> getPosts() throws NoSuchElementException {
        return repo.findAll();
    }

    @Override
    public void createPost(FreeboardPost post) {
        repo.save(post);
    }

    @Override
    public FreeboardPost getPost(Long postId) {
        return repo.findById(postId).get();
    }

    @Override
    public void deletePost(Long postId) {
        repo.deleteById(postId);
    }
}
