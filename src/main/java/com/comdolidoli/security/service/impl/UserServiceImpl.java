package com.comdolidoli.security.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comdolidoli.security.domain.Role;
import com.comdolidoli.security.dto.UserDto;
import com.comdolidoli.security.dto.UserFormDto;
import com.comdolidoli.security.entity.User;
import com.comdolidoli.security.repository.UserRepository;
import com.comdolidoli.security.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserFormDto userFormDto) {

        // 이메일 중복 확인
        if(userRepository.findByEmail(userFormDto.getEmail()) != null){
            return null;
        }
        // 가입한 성공한 모든 유저는  "USER" 권한 부여
        User user = userRepository.save(User.builder()
                                        .password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
                                        .email(userFormDto.getEmail())
                                        .role(Role.USER)
                                        .build());
        return UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .build();
    }

}
