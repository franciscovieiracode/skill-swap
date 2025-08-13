package com.skill_swap.skill_swap.controllers;

import com.skill_swap.skill_swap.dto.AddOfferedSkillsDto;
import com.skill_swap.skill_swap.dto.OfferedSkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.models.UserOfferedSkill;
import com.skill_swap.skill_swap.repositories.UserOfferedSkillsRepository;
import com.skill_swap.skill_swap.services.UserOfferedSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userOfferedSkills")
public class UserOfferedSkillsController {

    @Autowired
    UserOfferedSkillsService userOfferedSkillsService;

    @PostMapping("/addSkill")
    public ResponseEntity<SkillsResponseDto> addSkill(@RequestBody AddOfferedSkillsDto addOfferedSkillsDto, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(userOfferedSkillsService.addUserOfferedSkill(addOfferedSkillsDto, userDetails.getUsername()));
    }

    //get home skills with or without filers
    @GetMapping("/getAllOfferedSkills")
    public ResponseEntity<List<OfferedSkillsDto>> getAllOfferedSkills(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String dateRange,
            @RequestParam(required = false) Boolean sortByExperience) {
        return ResponseEntity.status(HttpStatus.OK).body(userOfferedSkillsService.getAllOfferedSkills(category, dateRange, sortByExperience));
    }

    @GetMapping("/getOfferedSkillsByUser")
    public ResponseEntity<List<OfferedSkillsDto>> getOfferedSkillsByUser(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(userOfferedSkillsService.getOfferedSkillsById(userDetails.getUsername()));
    }

    @GetMapping("/getOfferedSkillById/{id}")
    public ResponseEntity<OfferedSkillsDto> getOfferedSkillById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userOfferedSkillsService.getOfferedSkillById(id));
    }

}
