package com.comdolidoli.security.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.comdolidoli.security.dto.UserDto;
import com.comdolidoli.security.repository.UserRepository;

import java.util.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserRepository userRepository;
    @RequestMapping("/login")
    String loginView(){
        return "login";
    }

    @RequestMapping("/fail")
    String failView(){
        return "fail";
    }
    @RequestMapping("/admin")
    ModelAndView andView(){
        List<UserDto> userDtolist = userRepository.findAll().stream().map(u -> UserDto.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).role(u.getRole()).build()).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("/admin");
        modelAndView.addObject("userList",userDtolist);

        return modelAndView;
    }
    @RequestMapping("/myinfo")
    ModelAndView myView(Authentication authentication) {
        UserDto userDTO = Optional.ofNullable(userRepository.findByEmail(authentication.getName()))
        .map(u -> UserDto.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).role(u.getRole()).build())
        .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        ModelAndView modelAndView = new ModelAndView("myinfo");
        modelAndView.addObject("user", userDTO);

        return modelAndView;
    }
    @RequestMapping("/signup")
    String signupView(){
        return "signup";
    }
}
