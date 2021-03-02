package com.conexa.agenda.controller;

import com.conexa.agenda.dto.AgendamentoDTO;
import com.conexa.agenda.dto.CustomResponseDto;
import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.service.PacienteService;
import com.conexa.agenda.service.PacienteServiceImpl;
import com.conexa.agenda.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Autowired
    private CustomResponse customResponse;

    @RequestMapping(value = "/",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Paciente>> gelAll() {
        return new ResponseEntity<List<Paciente>>(pacienteService.getAll() , HttpStatus.OK);
    }

    @PostMapping(value = "/save",produces = "application/json")
    public ResponseEntity<Paciente> save(@RequestBody Paciente paciente) {
        Paciente pct = pacienteService.save(paciente);
        if(pct == null) {
            return new ResponseEntity<Paciente>(paciente, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update",produces = "application/json")
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
        Paciente pct = pacienteService.update(paciente);
        if(pct == null) {
            return new ResponseEntity<Paciente>(pct, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Paciente>(pct , HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Paciente> findById(@PathVariable String id) {
        Optional<Paciente> pacienteOptional = pacienteService.getById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente= pacienteOptional.get();
            return new ResponseEntity<Paciente>(paciente , HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponseDto> delete(@PathVariable String id) {
        HttpStatus statusCode = pacienteService.delete(id);
        if(statusCode != HttpStatus.OK) {
            return new ResponseEntity<CustomResponseDto>(customResponse.getCustomResponseDto("Erro ao deletar paciente.",statusCode), statusCode);
        }
        return new ResponseEntity<CustomResponseDto>(customResponse.getCustomResponseDto(
                "Paciente deletado com sucesso.", HttpStatus.OK), HttpStatus.OK);
    }
}


