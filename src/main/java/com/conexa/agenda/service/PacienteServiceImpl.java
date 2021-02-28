package com.conexa.agenda.service;

import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
        try {
        return pacienteRepository.save(paciente);
        }catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Paciente update(Paciente paciente) {
        try {
            return pacienteRepository.saveAndFlush(paciente);
        }catch (Exception ex) {
            return null;
        }

    }

    @Override
    public Optional<Paciente> getById(String pacienteId) {
        return pacienteRepository.findById(Integer.valueOf(pacienteId));
    }

    @Override
    public HttpStatus delete(String pacienteId) {
        try {
            pacienteRepository.deleteById(Integer.valueOf(pacienteId));
            return HttpStatus.OK;
        }catch (EmptyResultDataAccessException ex) {
            return HttpStatus.NOT_FOUND;
        }catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
