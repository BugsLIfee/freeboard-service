package com.erbf.bugsLife.freeboard.repository;

import com.erbf.bugsLife.freeboard.domain.FreeboardPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeboardPostRepo extends JpaRepository<FreeboardPost, Long> {
}
