package com.conexa.agenda.service;

import com.conexa.agenda.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> getAll();
    Paciente save(Paciente paciente);
    Paciente update(Paciente paciente);
    Optional<Paciente> getById(String pacienteId);
    String delete(String pacienteId);

}
