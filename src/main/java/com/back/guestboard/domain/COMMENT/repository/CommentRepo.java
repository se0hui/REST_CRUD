package com.back.guestboard.domain.COMMENT.repository;

import com.back.guestboard.domain.COMMENT.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardId(Long boardId);
}

//findBY~~ -> entity에 있는 속성으로 entity 조회가 가능합니다.