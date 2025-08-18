package com.skill_swap.skill_swap.services;
import com.skill_swap.skill_swap.dto.AddOfferedSkillsDto;
import com.skill_swap.skill_swap.dto.OfferedSkillsDto;
import com.skill_swap.skill_swap.dto.SkillsResponseDto;
import com.skill_swap.skill_swap.exceptions.UserOfferedSkillsException;
import com.skill_swap.skill_swap.models.Skill;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.models.UserOfferedSkill;
import com.skill_swap.skill_swap.repositories.UserRepository;
import com.skill_swap.skill_swap.repositories.SkillsRepository;
import com.skill_swap.skill_swap.repositories.UserOfferedSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Comparator;
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
    UserRepository userRepository;

    public SkillsResponseDto addUserOfferedSkill(AddOfferedSkillsDto addOfferedSkillsDto, String email){

        //get user
        User user = userRepository.findByEmail(email);

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

    public List<OfferedSkillsDto> getAllOfferedSkills(String category, String dateRange, Boolean sortByExperience){

        List<UserOfferedSkill> offeredSkills = offeredSkillsRepository.findAll();

        // Filter by category
        if (category != null && !category.isEmpty()) {
            offeredSkills = offeredSkills.stream()
                    .filter(x -> x.getSkill().getCategory().equals(category))
                    .toList();
        }

        // Filter by date range
        if (!dateRange.equals("noRange")) {
            LocalDate cutoffDate = LocalDate.now();
            if ("last7d".equalsIgnoreCase(dateRange)) {
                cutoffDate = cutoffDate.minusDays(7);
            } else if ("last30d".equalsIgnoreCase(dateRange)) {
                cutoffDate = cutoffDate.minusDays(30);
            }

            LocalDate finalCutoffDate = cutoffDate;
            offeredSkills = offeredSkills.stream()
                    .filter(x -> x.getCreatedAt().toLocalDate().isAfter(finalCutoffDate))
                    .toList();
        }

        // Sort by points descending
        if (Boolean.TRUE.equals(sortByExperience)) {
            offeredSkills = offeredSkills.stream()
                    .sorted(Comparator.comparingInt(UserOfferedSkill::getExperience).reversed())
                    .toList();
        }

        // Map to DTO only once
        return offeredSkills.stream()
                .map(offered -> new OfferedSkillsDto(
                        offered.getId(),
                        offered.getSkill().getId(),
                        offered.getSkill().getName(),
                        offered.getSkill().getCategory(),
                        offered.getUser().getId(),
                        offered.getUser().getName(),
                        offered.getExperience(),
                        offered.getNotes(),
                        offered.getCreatedAt()
                ))
                .toList();
    }


    public List<OfferedSkillsDto> getOfferedSkillsById(String email){

        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UserOfferedSkillsException("Invalid User Token");

        List<UserOfferedSkill> offeredSkills = offeredSkillsRepository.findByUserId(user.getId());

        return offeredSkills.stream().map(skill -> new OfferedSkillsDto(
                skill.getId(),
                skill.getSkill().getId(),
                skill.getSkill().getName(),
                skill.getSkill().getCategory(),
                user.getId(),
                user.getName(),
                skill.getExperience(),
                skill.getNotes(),
                skill.getCreatedAt()
        )).toList();    }

    public OfferedSkillsDto getOfferedSkillById(String id){

        Optional<UserOfferedSkill> userOfferedSkill = offeredSkillsRepository.findById(UUID.fromString(id));

        if (userOfferedSkill.isEmpty())
            throw new UserOfferedSkillsException("Invalid Skill");

        return new OfferedSkillsDto(
                userOfferedSkill.get().getId(),
                userOfferedSkill.get().getSkill().getId(),
                userOfferedSkill.get().getSkill().getName(),
                userOfferedSkill.get().getSkill().getCategory(),
                userOfferedSkill.get().getUser().getId(),
                userOfferedSkill.get().getUser().getName(),
                userOfferedSkill.get().getExperience(),
                userOfferedSkill.get().getNotes(),
                userOfferedSkill.get().getCreatedAt()

        );
    }

}
