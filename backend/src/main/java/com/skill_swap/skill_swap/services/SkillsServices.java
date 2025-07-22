package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.SkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillsServices {

    @Autowired
    SkillsRepository skillsRepository;

    public SkillsResponseDto addSkill(SkillsDto skillsDto){

        Skill skill = new Skill();
        skill.setCategory(skillsDto.category());
        skill.setName(skillsDto.name());
        skill.setDescription(skillsDto.description());

        skillsRepository.save(skill);

        return new SkillsResponseDto("Created");
    }

}
