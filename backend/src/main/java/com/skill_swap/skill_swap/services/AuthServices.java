package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.AuthDataDto;
import com.skill_swap.skill_swap.dto.AuthResponseDto;
import com.skill_swap.skill_swap.exceptions.AuthException;
import com.skill_swap.skill_swap.jwt.JwtUtils;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.repositories.AuthRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServices {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Transactional
    public AuthResponseDto signup(AuthDataDto authDataDto, HttpServletResponse httpServletResponse){

        User userTemp = authRepository.findByEmail(authDataDto.email());

        if (userTemp != null)
            throw new AuthException("User Already Exists");


        User user = new User();
        user.setEmail(authDataDto.email());
        user.setName(authDataDto.name());
        String hashedPassword = passwordEncoder.encode(authDataDto.password());
        user.setPassword(hashedPassword);
        user.setPoints(0);
        user.setTimeZone(authDataDto.timeZone());

        authRepository.save(user);

        String jwtToken = jwtUtils.generateToken(user.getEmail());

        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 24 * 24); // 1 day

        httpServletResponse.addCookie(cookie);

        return  new AuthResponseDto(jwtToken);
    }

    public AuthResponseDto test(UserDetails userDetails){

        if (userDetails != null) {
            return new AuthResponseDto(userDetails.getUsername());
        }

        throw new  AuthException("Invalid User");
    }

    public AuthResponseDto login(AuthDataDto authDataDto, HttpServletResponse httpServletResponse){

        User userTemp = authRepository.findByEmail(authDataDto.email());

        if (userTemp == null)
            throw new AuthException("User not found");

        if (!passwordEncoder.matches(authDataDto.password(), userTemp.getPassword()))
            throw new AuthException("Invalid Password");

        String jwtToken = jwtUtils.generateToken(authDataDto.email());

        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 24 * 24); // 1 day

        httpServletResponse.addCookie(cookie);

        return  new AuthResponseDto(jwtToken);
    }

}
