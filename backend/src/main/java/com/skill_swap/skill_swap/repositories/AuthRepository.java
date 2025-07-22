package com.skill_swap.skill_swap.repositories;

import com.skill_swap.skill_swap.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);


}
