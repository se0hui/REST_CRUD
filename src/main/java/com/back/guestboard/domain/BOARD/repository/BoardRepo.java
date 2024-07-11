package com.back.guestboard.domain.BOARD.repository;

import com.back.guestboard.domain.BOARD.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {

}
