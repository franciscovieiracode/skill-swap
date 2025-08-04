package com.skill_swap.skill_swap.services;
import com.skill_swap.skill_swap.dto.AddOfferedSkillsDto;
import com.skill_swap.skill_swap.dto.OfferedSkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.exceptions.UserOfferedSkillsException;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.models.UserOfferedSkill;
import com.skill_swap.skill_swap.repositories.AuthRepository;
import com.skill_swap.skill_swap.repositories.SkillsRepository;
import com.skill_swap.skill_swap.repositories.UserOfferedSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserOfferedSkillsService {

    @Autowired
    UserOfferedSkillsRepository offeredSkillsRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    AuthRepository authRepository;

    public SkillsResponseDto addUserOfferedSkill(AddOfferedSkillsDto addOfferedSkillsDto, String email){

        //get user
        User user = authRepository.findByEmail(email);

        if (user == null)
            throw new UserOfferedSkillsException("Invalid User Token");
        //get skill

        Optional<Skill> skill = skillsRepository.findById(addOfferedSkillsDto.skillId());

        if (skill.isEmpty())
            throw new UserOfferedSkillsException("Invalid Skill");

        if (addOfferedSkillsDto.experience() < 1 || addOfferedSkillsDto.experience() >5)
            throw new UserOfferedSkillsException("Invalid Experience");

        UserOfferedSkill userOfferedSkill = new UserOfferedSkill();
        userOfferedSkill.setUser(user);
        userOfferedSkill.setSkill(skill.get());
        userOfferedSkill.setNotes(addOfferedSkillsDto.notes());
        userOfferedSkill.setExperience(addOfferedSkillsDto.experience());
        userOfferedSkill.setCreatedAt(ZonedDateTime.now());

        offeredSkillsRepository.save(userOfferedSkill);

        return new SkillsResponseDto("Added Skill to User");
    }

    public List<OfferedSkillsDto> getAllOfferedSkills(){
        return offeredSkillsRepository.findAll().stream().map(offered -> new OfferedSkillsDto(
                offered.getId(),
                offered.getSkill().getName(),
                offered.getSkill().getCategory(),
                offered.getUser().getName(),
                offered.getExperience(),
                offered.getNotes()
        )).toList();
    }

    public List<OfferedSkillsDto> getOfferedSkillsById(String email){

        User user = authRepository.findByEmail(email);
        if (user == null)
            throw new UserOfferedSkillsException("Invalid User Token");

        List<UserOfferedSkill> offeredSkills = offeredSkillsRepository.findByUserId(user.getId());

        return offeredSkills.stream().map(skill -> new OfferedSkillsDto(
                skill.getId(),
                skill.getSkill().getName(),
                skill.getSkill().getCategory(),
                user.getName(),
                skill.getExperience(),
                skill.getNotes()
        )).toList();    }
}
