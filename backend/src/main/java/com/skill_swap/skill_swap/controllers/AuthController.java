package com.skill_swap.skill_swap.controllers;

import com.skill_swap.skill_swap.dto.AuthDataDto;
import com.skill_swap.skill_swap.dto.AuthResponseDto;
import com.skill_swap.skill_swap.repositories.AuthRepository;
import com.skill_swap.skill_swap.services.AuthServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthDataDto authDataDto, HttpServletResponse httpServletResponse){
        return ResponseEntity.status(HttpStatus.CREATED).body(authServices.signup(authDataDto, httpServletResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthDataDto authDataDto, HttpServletResponse httpServletResponse){
        return ResponseEntity.status(HttpStatus.OK).body(authServices.login(authDataDto, httpServletResponse));
    }

    @GetMapping("/test")
    public ResponseEntity<AuthResponseDto> test(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(authServices.test(userDetails));
    }

}
