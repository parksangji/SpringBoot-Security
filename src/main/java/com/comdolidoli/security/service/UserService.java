package com.comdolidoli.security.service;

import com.comdolidoli.security.dto.UserDto;
import com.comdolidoli.security.dto.UserFormDto;

public interface UserService {
    UserDto createUser(UserFormDto userFormDto); // 유저 회원 가입
}
