package com.conexa.agenda.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
public class CustomResponseDto {
    private String message;
    private HttpStatus status;
}
