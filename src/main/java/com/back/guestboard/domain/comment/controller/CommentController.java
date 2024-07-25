package com.back.guestboard.domain.comment.controller;

import com.back.guestboard.domain.user.repository.UserRepo;
import com.back.guestboard.global.response.Response;
import com.back.guestboard.domain.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserRepo userRepo;


    //댓글 모두 불러오기
    @ApiOperation(value = "댓글 불러오기", notes = "게시글에 달린 댓글을 모두 불러옴")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{boardId}")
    public Response getComments(@PathVariable("boardId") Long boardId){
        return new Response("성공", "댓글을 불러왔습니다.", commentService.getComments(boardId));
    }

    //댓글 삭제
    @ApiOperation(value = "댓글 삭제", notes = "게시글에 달린 댓글을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{boardId}/{commentId}")
    public Response deleteComment(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId){

        return new Response("성공", "댓글 삭제 완료", commentService.deleteComment(commentId));
    }
}
