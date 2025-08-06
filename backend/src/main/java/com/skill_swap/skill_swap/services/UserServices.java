package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.CurrentUserContextDto;
import com.skill_swap.skill_swap.exceptions.AuthException;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public CurrentUserContextDto getCurrentUser(String email){

        User user = userRepository.findByEmail(email);

        if (user != null)
            return  new CurrentUserContextDto(user.getName(), user.getEmail(), user.getTimeZone(), user.getPoints());
        else
            throw new UsernameNotFoundException("User not Found");
    }

}
