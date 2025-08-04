package com.skill_swap.skill_swap.repositories;

import com.skill_swap.skill_swap.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillsRepository extends JpaRepository<Skill, UUID> {

    Skill findByName(String name);

}
