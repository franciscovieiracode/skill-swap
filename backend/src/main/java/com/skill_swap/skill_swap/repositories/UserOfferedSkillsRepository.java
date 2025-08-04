package com.skill_swap.skill_swap.repositories;

import com.skill_swap.skill_swap.models.UserOfferedSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserOfferedSkillsRepository extends JpaRepository<UserOfferedSkill, UUID> {

    List<UserOfferedSkill> findByUserId(UUID userId);

}
