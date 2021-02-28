package com.conexa.agenda.controller;

import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.service.PacienteService;
import com.conexa.agenda.service.PacienteServiceImpl;
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

    @RequestMapping(value = "/",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Paciente>> gelAll() {
        return new ResponseEntity<List<Paciente>>(pacienteService.getAll() , HttpStatus.OK);
    }

    @PostMapping(value = "/save",produces = "application/json")
    public ResponseEntity<Paciente> save(@RequestBody Paciente paciente) {
        pacienteService.save(paciente);

        return new ResponseEntity<Paciente>(paciente , HttpStatus.CREATED);
    }

    @PutMapping(value = "/update",produces = "application/json")
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
        pacienteService.update(paciente);

        return new ResponseEntity<Paciente>(paciente , HttpStatus.OK);
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
    public ResponseEntity<String> delete(@PathVariable String id) {
      pacienteService.delete(id);
        return new ResponseEntity<String>("Deletado com Sucesso" , HttpStatus.OK);
    }

}


