package com.back.guestboard.domain.USER.repository;

import com.back.guestboard.domain.USER.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
