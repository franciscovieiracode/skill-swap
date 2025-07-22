package com.skill_swap.skill_swap.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_desired_skill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDesiredSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "priority")
    private int priority;

    @Column(name = "notes")
    private String notes;

    @Column(name = "createdAt")
    private LocalDate createdAt;
}
