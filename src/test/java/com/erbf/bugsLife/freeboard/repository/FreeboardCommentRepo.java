package com.erbf.bugsLife.freeboard.repository;

import com.erbf.bugsLife.freeboard.domain.FreeboardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeboardCommentRepo extends JpaRepository<FreeboardComment, Long> {

    public List<FreeboardComment> findByPostId(Long postId);
//    public List<FreeboardComment> findAllComment(@Param("postId") Long postId);
}
