package com.skill_swap.skill_swap.controllers;

import com.skill_swap.skill_swap.dto.CreateRequestDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.services.SkillRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requests")
public class SkillRequestController {

    @Autowired
    SkillRequestService skillRequestService;


    @PostMapping("/createRequest")
    public ResponseEntity<SkillsResponseDto> addRequest(@RequestBody CreateRequestDto createRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillRequestService.addRequest(createRequestDto, userDetails));
    }

}
