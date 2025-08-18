package com.skill_swap.skill_swap.dto;

import java.util.UUID;

public record CreateRequestDto(
    UUID receiptingId,
    String skillId
) {
}
