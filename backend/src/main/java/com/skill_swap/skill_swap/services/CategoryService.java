package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.CategoryDto;
import com.skill_swap.skill_swap.exceptions.CategoryException;
import com.skill_swap.skill_swap.models.Category;
import com.skill_swap.skill_swap.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(CategoryDto categoryDto){

        if (categoryDto.name().isEmpty())
            throw new CategoryException("Invalid Category Name");

        Category categoryTemp = categoryRepository.findByName(categoryDto.name());

        if (categoryTemp!= null)
            throw new CategoryException("Category already exists");

        Category category = new Category();
        category.setName(categoryDto.name());

        return categoryRepository.save(category);
    }

    public List<CategoryDto> categoryList(){
        return categoryRepository.findAll().stream().map( category -> new CategoryDto(
                category.getId(),
                category.getName())
        ).toList();
    }

}
