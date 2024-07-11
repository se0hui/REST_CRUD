package com.back.guestboard.domain.BOARD.service;


import com.back.guestboard.domain.BOARD.dto.BoardDto;
import com.back.guestboard.domain.BOARD.entity.Board;
import com.back.guestboard.domain.BOARD.repository.BoardRepo;
import com.back.guestboard.domain.USER.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepo boardRepo;

    //전체 게시물 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getBoards() {
        List<Board> boards = boardRepo.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(BoardDto.toDto(s)));
        return boardDtos;
    }

    //특정 게시물 조회
    @Transactional(readOnly = true)
    public BoardDto getBoard(Long id){
        Board board = boardRepo.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다.");
        });

        return BoardDto.toDto(board);
    }

    //게시물 작성
    @Transactional
    public BoardDto write(BoardDto boardDto, User user){
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .user(user)
                .build();
        boardRepo.save(board);
        return BoardDto.toDto(board);
    }

    //게시물 수정
    @Transactional
    public BoardDto update(Long id, BoardDto boardDto){
        Board board = boardRepo.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다!");
        });

        board.update(boardDto.getTitle(), boardDto.getContent());

        boardRepo.save(board);

        return BoardDto.toDto(board);
    }

    //게시글 삭제
    @Transactional
    public void delete(Long id){
        //id를 기반으로, 게시글이 존재하는지 먼저 찾음
        //게시글이 없다면 오류 처리
        Board board = boardRepo.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다!");
        });

        //게시글이 있는 경우 삭제처리
        boardRepo.deleteById(id);
    }
}
