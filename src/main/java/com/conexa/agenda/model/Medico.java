package com.conexa.agenda.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
@Table(name="medico")
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="medico_id")
    private int medicoId;
    public String token;
    @Column(name="nome")
    public String nome;
    @Column(name="especialidade")
    public String especialidade;
    public List<Agendamento> agendamentos_hoje;
}