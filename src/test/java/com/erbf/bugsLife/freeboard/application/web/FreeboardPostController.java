package com.erbf.bugsLife.freeboard.application.web;

import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardAddPostDto;
import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardPostDto;
import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import com.erbf.bugsLife.freeboard.domain.PasswordEncoding;
import com.erbf.bugsLife.freeboard.repository.FreeboardPostRepo;
import com.erbf.bugsLife.freeboard.service.FreeboardPostService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/freeboard/")
public class FreeboardPostController {

    @Autowired
    FreeboardPostService service;

    @Autowired
    FreeboardPostRepo repo;

    @GetMapping("/")
    public List<FreeboardPostDto> getPostList(){
        List<FreeboardPost> posts = service.getPosts();
        return posts.stream().map(post -> new FreeboardPostDto(post)).collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    public FreeboardPostDto getPost(@PathVariable Long postId){
        FreeboardPost post = service.getPost(postId);
        return new FreeboardPostDto(post);
    }

    @PutMapping("/{postId}")
    public void addLikes(@PathVariable Long postId){
        FreeboardPost post = service.getPost(postId);

        Gson json = new Gson();
        post.addLikes();
        System.out.println(json.toJson(post));
        repo.save(post);
    }

    @PutMapping("/{postId}/dislike")
    public void disLikes(@PathVariable Long postId){
        FreeboardPost post = service.getPost(postId);

        Gson json = new Gson();
        post.disLikes();
        System.out.println(json.toJson(post));
        repo.save(post);
    }

    @PutMapping("/{postId}/view")
    public void increaseViewCnt(@PathVariable Long postId){
        FreeboardPost post = service.getPost(postId);
        post.addViewCnt();
        repo.save(post);
    }

    @PostMapping
    public FreeboardAddPostDto createPost(@RequestBody FreeboardAddPostDto post){
        service.createPost(post.toEntity());
        return post;
    }


    //post 삭제
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, @RequestParam String pwd){
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        System.out.println("==========");
        System.out.println(pwd);
//        System.out.println(service.getPost(postId).getPwd().toString());

        if (passwordEncoding.matches(pwd, service.getPost(postId).getPwd()))
            service.deletePost(postId);
        else
            return "포스트 삭제 에러";
        return "Freeboard post 삭제 완료";

    }

}
