package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.SkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.exceptions.SkillException;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SkillsServices {

    @Autowired
    SkillsRepository skillsRepository;

    public SkillsResponseDto addSkill(SkillsDto skillsDto){

        if (skillsRepository.findByName(skillsDto.name()) != null)
            throw new SkillException("Skill already Exists");

        Skill skill = new Skill();
        skill.setCategory(skillsDto.category());
        skill.setName(skillsDto.name());
        skill.setDescription(skillsDto.description());

        skillsRepository.save(skill);

        return new SkillsResponseDto("Created");
    }

    public SkillsResponseDto removeSkill(SkillsDto skillsDto){

        if (skillsRepository.findById(skillsDto.id()).isEmpty() )
            throw new SkillException("Skill does not exist");

        skillsRepository.deleteById(skillsDto.id());

        return new SkillsResponseDto("Deleted Skill");
    }



}
