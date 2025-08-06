package com.skill_swap.skill_swap.controllers;

import com.skill_swap.skill_swap.dto.CurrentUserContextDto;
import com.skill_swap.skill_swap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    UserServices userServices;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<CurrentUserContextDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.getCurrentUser(userDetails.getUsername()));
    }

}
