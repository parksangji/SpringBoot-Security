package com.comdolidoli.security.dto;

import com.comdolidoli.security.domain.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Role role;
    @Builder
    public UserDto(Long id, String email, String password,Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
