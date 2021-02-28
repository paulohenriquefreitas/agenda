package com.conexa.agenda.service;

import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getAll() {
        return pacienteRepository.findAll();

    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return pacienteRepository.saveAndFlush(paciente);
    }

    @Override
    public Optional<Paciente> getById(String pacienteId) {
        return pacienteRepository.findById(Integer.valueOf(pacienteId));
    }

    @Override
    public String delete(String pacienteId) {
        pacienteRepository.deleteById(Integer.valueOf(pacienteId));
        return null;
    }
}
