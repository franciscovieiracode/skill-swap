package com.skill_swap.skill_swap.dto;

public record CurrentUserContextDto(
        String name,
        String email,
        String timeZone,
        int points
) {
}
