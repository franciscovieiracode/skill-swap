package com.skill_swap.skill_swap.exceptions;

import com.skill_swap.skill_swap.dto.ApiErrorDto;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiErrorDto> authError(AuthException ex){
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SkillException.class)
    public ResponseEntity<ApiErrorDto> skillError(SkillException ex){
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserOfferedSkillsException.class)
    public ResponseEntity<ApiErrorDto> offeredSkillError(UserOfferedSkillsException ex){
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ApiErrorDto> offeredSkillError(CategoryException ex){
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

}
