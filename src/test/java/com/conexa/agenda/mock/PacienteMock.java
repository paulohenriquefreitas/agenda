package com.conexa.agenda.mock;

import com.conexa.agenda.model.Paciente;

public class PacienteMock {
    public static Paciente setPaciente(String nome, String cpf, int idade, String telefone) {
        return Paciente.builder()
                .nome(nome)
                .cpf(cpf)
                .idade(idade)
                .telefone(telefone)
                .build();

    }
}
