package com.skill_swap.skill_swap.models;

import com.skill_swap.skill_swap.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "skill_request", uniqueConstraints = @UniqueConstraint(
        columnNames = {"requester_id","user_offered_skill_id"}
))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private UserOfferedSkill userOfferedSkill;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User recipient;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private LocalDate localDate;
}
