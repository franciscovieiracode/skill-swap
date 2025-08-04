package com.skill_swap.skill_swap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiErrorDto {
    private int status;
    private String message;

    public ApiErrorDto(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
    }
}
