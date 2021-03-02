package com.conexa.agenda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder

public class AgendamentoDTO {

    @JsonProperty("id_paciente")
    private Long idPaciente;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("data_hora_atendimento")
    private LocalDateTime dataHoraAgendamento;

}