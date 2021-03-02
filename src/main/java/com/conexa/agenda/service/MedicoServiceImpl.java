package com.conexa.agenda.service;

import com.conexa.agenda.exception.CustomException;
import com.conexa.agenda.model.Agendamento;
import com.conexa.agenda.model.Medico;
import com.conexa.agenda.repository.AgendamentoRepository;
import com.conexa.agenda.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    @Override
    public Optional<Medico> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Medico> findByName(String username) {
        return medicoRepository.findByName(username);
    }

    @Override
    public void save(Medico medico) {
        medicoRepository.save(medico);
    }

    @Override
    public Agendamento realizarAgendamento(Agendamento agendamento) {
        agendamento.setMedico(this.obterUsuarioLogado().get());
        return agendamentoRepository.save(agendamento);
    }
    @Override
    public Optional<Medico> obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomException("Usuário não autenticado, favor realizar o login novamente.", HttpStatus.NOT_FOUND);
        }

        User usetr = (User) authentication.getPrincipal();
        return this.findByName(usetr.getUsername());
    }

    public List<Agendamento> getAgendamentosDoDiaAtual(List<Agendamento> agendamentos, LocalDate hoje) {
        return agendamentos.stream()
                .filter(agendamento -> agendamento.getDataHoraAtendimento()
                        .toLocalDate().equals(hoje))
                .collect(Collectors.toList());
    }
}
