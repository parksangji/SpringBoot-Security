package com.comdolidoli.security.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comdolidoli.security.dto.PackageDto;
import com.comdolidoli.security.dto.UserDto;
import com.comdolidoli.security.dto.UserFormDto;
import com.comdolidoli.security.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public PackageDto getPackDto(){
        PackageDto packageDto = new PackageDto();
        packageDto.setNew(false);

        return packageDto;
    }
    @PostMapping("/signup")
    public String createUser(UserFormDto userFormDto) {
        UserDto userDto = userService.createUser(userFormDto);
        if(userDto == null){
            return "failsignup";
        }else{
            return "login";
        }
    }
    @GetMapping("/logout")
    public void logoutPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/login");
    }
}
