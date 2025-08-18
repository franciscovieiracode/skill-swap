package com.skill_swap.skill_swap.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record OfferedSkillsDto(
    UUID id,
    UUID skillId,
    String skillName,
    String skillCategory,
    UUID userId,
    String userName,
    int experience,
    String notes,
    ZonedDateTime created_at
) {}
