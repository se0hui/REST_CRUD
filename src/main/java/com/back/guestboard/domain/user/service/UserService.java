package com.back.guestboard.domain.user.service;

import com.back.guestboard.domain.user.dto.RegisterDto;
import com.back.guestboard.domain.user.entity.User;
import com.back.guestboard.domain.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User resister(RegisterDto registerDto){
        User user = User.builder()
                .name(registerDto.getName())
                .password(registerDto.getPassword())
                .username(registerDto.getUsername())
                .build();
        return userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findUser(int id){
        return userRepo.findById(id).orElseThrow(()->{return new IllegalArgumentException("User ID를 찾을 수 없습니다.");});
    }
}
