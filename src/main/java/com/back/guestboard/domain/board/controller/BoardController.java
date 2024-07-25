package com.back.guestboard.domain.board.controller;


import com.back.guestboard.domain.board.dto.BoardDto;
import com.back.guestboard.domain.user.entity.User;
import com.back.guestboard.domain.user.repository.UserRepo;
import com.back.guestboard.global.response.Response;
import com.back.guestboard.domain.board.service.BoardService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")


public class BoardController {

    private final BoardService boardService;
    private final UserRepo userRepo;

    //전체 게시글 조회
    @ApiOperation(value = "전체 게시글 보기", notes = "전체 게시글을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Response getBoards(){
        return new Response("성공", "전체 게시물 리턴", boardService.getBoards());
    }

    //개별 게시글 조회
    @ApiOperation(value = "개별 게시글 조회", notes = "개별 게시글을 조회한다")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response getBoard(@PathVariable("id") Long id) {
        return new Response("성공", "개별 게시물 반환", boardService.getBoard(id));
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/write")
    public Response write(@RequestBody BoardDto boardDto){

        User user = userRepo.findById(1).get();
        return new Response("성공", "글 작성 성공", boardService.write(boardDto, user));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //200 OK -> 요청이 성공적으로 되었음
    //204 No Content -> 요청에 대해서 보내줄 수 있는 콘텐츠가 없지만 헤더는 의미 있을 수 있음
    @PutMapping("/update/{id}")
    public Response edit(@RequestBody BoardDto boardDto, @PathVariable("id") Long id){
        //고정된 USER ID 사용
        User user = userRepo.findById(1).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다"));
        return new Response("성공", "글 수정 성공", boardService.update(id, boardDto));
    }


    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id){

        boardService.delete(id);
        return new Response("성공", "글 삭제 성공", null);
    }

}

