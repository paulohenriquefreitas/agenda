package com.conexa.agenda.service;

import com.conexa.agenda.model.Agendamento;
import com.conexa.agenda.model.Medico;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicoService {
    Optional<Medico> findById(Integer id);
    Optional<Medico> findByName(String username);
    void save(Medico medico);
    Optional<Medico> obterUsuarioLogado();
    Agendamento realizarAgendamento(Agendamento agendamento);
    List<Agendamento> getAgendamentosDoDiaAtual(List<Agendamento> agendamentos, LocalDate diaAtual);
}
