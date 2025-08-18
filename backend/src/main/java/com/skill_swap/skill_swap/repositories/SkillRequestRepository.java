package com.skill_swap.skill_swap.repositories;


import com.skill_swap.skill_swap.models.SkillRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRequestRepository extends JpaRepository<SkillRequest, UUID> {
}
