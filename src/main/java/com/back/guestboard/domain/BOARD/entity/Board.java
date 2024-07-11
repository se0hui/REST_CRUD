package com.back.guestboard.domain.BOARD.entity;


import com.back.guestboard.domain.USER.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 업데이트 메서드를 추가
    // 객체의 필드를 변경할 수 있음
    // 객체의 상태를 변경해야 할 때, 새로운 객체 생성이 아닌
    // 기존 객체의 필드를 업데이트 하는 것에 사용됨
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
