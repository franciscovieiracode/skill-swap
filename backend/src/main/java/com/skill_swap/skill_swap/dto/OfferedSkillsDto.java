package com.skill_swap.skill_swap.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record OfferedSkillsDto(
    UUID id,
    String skillName,
    String skillCategory,
    String userName,
    int experience,
    String notes,
    ZonedDateTime created_at
) {}
