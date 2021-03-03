package com.conexa.agenda.util;

import com.conexa.agenda.dto.CustomResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomResponse {

    public CustomResponseDto getCustomResponseDto(String message, HttpStatus statusCode) {
        CustomResponseDto customResponseDto = CustomResponseDto.builder()
                .message(message)
                .status(statusCode)
                .build();
        return customResponseDto;
    }
}
