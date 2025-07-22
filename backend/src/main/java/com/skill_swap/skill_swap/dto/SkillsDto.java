package com.skill_swap.skill_swap.dto;

import jakarta.validation.constraints.NotBlank;

public record SkillsDto (
    @NotBlank String name,
    @NotBlank String category,
    String description
) {

}

