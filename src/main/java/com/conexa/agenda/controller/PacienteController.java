package com.conexa.agenda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @RequestMapping("/")
    public String gelAlls() {
        return "Implementação inicial!";
    }
}


