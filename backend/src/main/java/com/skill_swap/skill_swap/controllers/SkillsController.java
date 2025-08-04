package com.skill_swap.skill_swap.controllers;

import com.skill_swap.skill_swap.dto.SkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.services.SkillsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillsController {

    @Autowired
    SkillsServices skillsServices;

    @PostMapping("/addSkill")
    public ResponseEntity<SkillsResponseDto> addSkills(@RequestBody SkillsDto skillsDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(skillsServices.addSkill(skillsDto));
    }

    @PostMapping("/removeSkill")
    public ResponseEntity<SkillsResponseDto> removeSkills(@RequestBody SkillsDto skillsDto){
        return ResponseEntity.status(HttpStatus.OK).body(skillsServices.removeSkill(skillsDto));
    }

}
