package com.comdolidoli.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 4, max = 100, message = "비밀번호는 4자 이상, 100자 이하로 입력해주세요.")
    private String password;

}
