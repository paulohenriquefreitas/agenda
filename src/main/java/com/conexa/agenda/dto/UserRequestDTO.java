package com.conexa.agenda.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class UserRequestDTO {

    private String username;
    private String password;
}
