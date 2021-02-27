package com.conexa.agenda.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name="agendamento_hoje")
public class Agendamento {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="paciente_id")
    public String pacienteId;
    @Column(name="data_hora_atendimento")
    public String dataHoraAgendamento;
}
