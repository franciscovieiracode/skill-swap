package com.skill_swap.skill_swap.services;

import com.skill_swap.skill_swap.dto.AuthDataDto;
import com.skill_swap.skill_swap.dto.AuthLogoutDto;
import com.skill_swap.skill_swap.dto.AuthResponseDto;
import com.skill_swap.skill_swap.exceptions.AuthException;
import com.skill_swap.skill_swap.jwt.JwtUtils;
import com.skill_swap.skill_swap.models.User;
import com.skill_swap.skill_swap.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Transactional
    public AuthResponseDto signup(AuthDataDto authDataDto, HttpServletResponse httpServletResponse){

        User userTemp = userRepository.findByEmail(authDataDto.email());

        if (userTemp != null)
            throw new AuthException("User Already Exists");


        User user = new User();
        user.setEmail(authDataDto.email());
        user.setName(authDataDto.name());
        String hashedPassword = passwordEncoder.encode(authDataDto.password());
        user.setPassword(hashedPassword);
        user.setPoints(0);
        user.setTimeZone(authDataDto.timeZone());

        userRepository.save(user);

        String jwtToken = jwtUtils.generateToken(user.getEmail());

        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 24 * 24); // 1 day

        httpServletResponse.addCookie(cookie);

        return  new AuthResponseDto(jwtToken);
    }

    public AuthLogoutDto logout(HttpServletResponse httpServletResponse){

        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);

        return new AuthLogoutDto(true);
    }

    public AuthResponseDto login(AuthDataDto authDataDto, HttpServletResponse httpServletResponse){

        User userTemp = userRepository.findByEmail(authDataDto.email());

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
