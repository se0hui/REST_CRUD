package com.back.guestboard.domain.board.dto;


import com.back.guestboard.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
// -> 데이터를 전달하는 DTO에서는 가독성과 불변성 이유로
// 생성자보다 Builder 패턴을 사용함

public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public static BoardDto toDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getUser().getName())
                .build();
    }

}
