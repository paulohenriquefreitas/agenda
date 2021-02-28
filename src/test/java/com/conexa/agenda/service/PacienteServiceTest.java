package com.conexa.agenda.service;

import com.conexa.agenda.mock.PacienteMock;
import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.repository.PacienteRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteServiceImpl getPacienteServiceImpl;

    @Mock
    private PacienteRepository pacienteRepository;

    private Paciente paciente;

    @Before("")
    public void beforeEachTest() {
        paciente = Mockito.mock(Paciente.class);
    }

    @DisplayName("Teste de salvar paciente")
    @Test
    void test_save_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteRepository.save(paciente)).thenReturn(paciente);
        assertEquals("Paulo", pacienteRepository.save(paciente).getNome());
    }

    @DisplayName("Teste de buscar todos os pacientes")
    @Test
    void test_getAll_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(paciente));
        assertEquals(1, pacienteRepository.findAll().size());
    }


    @DisplayName("Teste de buscar um paciente por id")
    @Test
    void test_getPacienteById_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteRepository.findById(1)).thenReturn(java.util.Optional.of(paciente));
        assertEquals("Paulo", pacienteRepository.findById(1).get().getNome());
    }

    @DisplayName("Atualizar um paciente")
    @Test
    void test_update_success() {
        paciente = PacienteMock.setPaciente("Jose", "5829482984", 25, "113134144");
        paciente.setNome("Antonio");
        when(pacienteRepository.saveAndFlush(paciente)).thenReturn(paciente);
        assertEquals("Antonio", pacienteRepository.saveAndFlush(paciente).getNome());
    }

    @DisplayName("Deletar um paciente")
    @Test
    void test_delete_success() {
        paciente = PacienteMock.setPaciente("Jose", "5829482984", 25, "113134144");
        pacienteRepository.save(paciente);
        pacienteRepository.deleteById(paciente.getId());
        assertEquals(0, pacienteRepository.findAll().size());
    }

}
