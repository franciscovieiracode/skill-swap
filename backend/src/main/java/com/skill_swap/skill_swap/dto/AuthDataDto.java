package com.skill_swap.skill_swap.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDataDto(
        @NotBlank String email,
        @NotBlank String password,
        String name,
        String timeZone
) {
}
