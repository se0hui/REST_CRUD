package com.back.guestboard.domain.COMMENT.service;

import com.back.guestboard.domain.BOARD.entity.Board;
import com.back.guestboard.domain.COMMENT.dto.CommentDto;
import com.back.guestboard.domain.COMMENT.entity.Comment;
import com.back.guestboard.domain.COMMENT.repository.CommentRepo;
import com.back.guestboard.domain.USER.entity.User;
import com.back.guestboard.domain.BOARD.repository.BoardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
//setter 메소드 사용을 지양해야 하는 이유
// setter 메소드를 사용하면 값을 변경한 의도를 파악하기 힘듦
// 객체의 일관성을 유지하기 어려움
// - setter는 public으로 언제든지 변경할 수 있는 상태가 되어 객체의 일관성을 유지하기 어려움

// 대체 방법
// RequiredArgsConstructor를 사용하여 생성자를 생성,
// 생성자를 통해 필드 값을 초기화

//builder 패턴 - 적용
// 요구사항에 맞게 필요한 데이터만 이용하여 유연한 클래스 생성이가능
// 다양한 생성자들이 사라지고 전체 생성자 하나만을 가지고 있는 형태로 변경
// 유지보수 및 가독성 향상, 객체를 생성할 때 인자 값의 순서가 상관이 없음
// 메서드 기반의 객체 생성
// 객체를 생성하고 필드 값을 설정하는 메서드 추가하여, 객체의 필드 변경
// 객체의 상태 변경을 명시적으로 처리할 수 있도록 한다


@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final BoardRepo boardRepo;

    // 댓글 작성하기
    @Transactional
    public CommentDto writeComment(Long boardId, CommentDto commentDto, User user) {
        //게시판 번호로 게시글 찾기
        Board board = boardRepo.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(user)
                .board(board)
                .build();

        commentRepo.save(comment);

        return CommentDto.toDto(comment);
    }

    //글에 해당하는 전체 댓글 불러오기
    @Transactional(readOnly = true)
    public List<CommentDto> getComments(Long boardId){
        List<Comment> comments = commentRepo.findAllByBoardId(boardId);
        List<CommentDto> commentDtos = new ArrayList<>();

        comments.forEach(s->commentDtos.add(CommentDto.toDto(s)));
        return commentDtos;
    }


    //댓글 삭제
    @Transactional
    public String deleteComment(Long commentId){
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->{
            return new IllegalArgumentException("댓글 ID를 찾을 수 없습니다.");
        });
        //commentRepo.deleteById(commentId);
        return "삭제 완료";
    }
}

//@Transactional 어노테이션은
//메소드가 전체 성공일 때, Commit을 해주고, 하나라도 실패한다면
//RollBack을 해준다고 보시면 됩니다.