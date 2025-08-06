package com.skill_swap.skill_swap.repositories;

import com.skill_swap.skill_swap.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);

}
