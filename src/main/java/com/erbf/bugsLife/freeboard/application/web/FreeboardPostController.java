package com.erbf.bugsLife.freeboard.application.web;

import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardAddPostDto;
import com.erbf.bugsLife.freeboard.application.web.dto.FreeboardModifyDto;
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
import java.util.Optional;
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
    public String deletePost(@PathVariable Long postId){
         service.deletePost(postId);
        return "Freeboard post 삭제 완료";

    }

    @GetMapping("/edit/{postId}")
    public Boolean pwdCheck(@PathVariable Long postId, @RequestParam String pwd){
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        Optional<FreeboardPost> originalPost = repo.findById(postId);

        System.out.println(passwordEncoding.matches(pwd, originalPost.get().getPwd()));
        if(passwordEncoding.matches(pwd, originalPost.get().getPwd())) {
            System.out.println(passwordEncoding.matches(pwd, originalPost.get().getPwd()));
           return true;
        }else {
            return false;
        }
    }



    @PutMapping("/edit/{postId}")
    public String modifyPost(@PathVariable Long postId, @RequestBody FreeboardModifyDto editPost){
        Optional<FreeboardPost> originalPost = repo.findById(postId);
        FreeboardPost newPost = editPost.toEntity(originalPost.get());

            repo.save(newPost);
            System.out.println("New Post ==========");
            return "포스트 수정 완료! ";
        }
    }




