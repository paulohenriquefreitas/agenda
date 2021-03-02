package com.conexa.agenda.controller;

import com.conexa.agenda.dto.AgendamentoDTO;
import com.conexa.agenda.model.Agendamento;
import com.conexa.agenda.service.MedicoService;
import com.conexa.agenda.service.PacienteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/medico")
@Api(tags = "medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService patienteService;



    @PostMapping("/save")
    @ApiOperation(value = "${MedicoController.save}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<AgendamentoDTO> save(@Validated @ApiParam("Agendamento")  @RequestBody AgendamentoDTO agendamentoDTO ) {
        Agendamento agendamento = Agendamento.builder()
                .dataHoraAtendimento(agendamentoDTO.getDataHoraAgendamento())
                .paciente(patienteService.getById(String.valueOf(agendamentoDTO.getIdPaciente())).get())
                .medico(medicoService.obterUsuarioLogado().get())
                .build();

        Agendamento agdt = medicoService.realizarAgendamento(agendamento);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AgendamentoDTO.builder()
                        .dataHoraAgendamento(agdt.getDataHoraAtendimento())
                        .idPaciente(agdt.getPaciente().getId())
                        .build()
                );
    }
}
