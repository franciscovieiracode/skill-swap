package com.skill_swap.skill_swap.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "points")
    private int points;

    @Column(name = "time_zone")
    private String timeZone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserOfferedSkill> userOfferedSkills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserDesiredSkill> userDesiredSkills;

}
