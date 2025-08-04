package com.skill_swap.skill_swap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddOfferedSkillsDto(
    @NotNull UUID skillId,
    @NotNull int experience,
    String notes
) {
}
