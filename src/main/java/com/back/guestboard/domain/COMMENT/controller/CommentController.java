package com.back.guestboard.domain.COMMENT.controller;


import com.back.guestboard.domain.COMMENT.dto.CommentDto;
import com.back.guestboard.domain.USER.entity.User;
import com.back.guestboard.domain.USER.repository.UserRepo;
import com.back.guestboard.global.response.Response;
import com.back.guestboard.domain.COMMENT.service.CommentService;
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

    //댓글 작성
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{boardId}")
    public Response writeComment(@PathVariable("boardId") Long boardId, @RequestBody CommentDto commentDto){

        User user = userRepo.findById(1).get();
        return new Response("성공", "댓글 작성을 완료했습니다", commentService.writeComment(boardId, commentDto, user));
    }

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
