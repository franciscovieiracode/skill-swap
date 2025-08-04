package com.skill_swap.skill_swap.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SkillsDto (
    @NotBlank String name,
    @NotBlank String category,
    String description,
    UUID id
) {

}

