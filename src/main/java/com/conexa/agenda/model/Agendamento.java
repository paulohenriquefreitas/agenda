package com.conexa.agenda.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name="agendamento")
public class Agendamento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="paciente_id")
    public String pacienteId;
    @Column(name="data_hora_atendimento")
    public String dataHoraAtendimento;
}
