package com.conexa.agenda.controller;

import com.conexa.agenda.mock.PacienteMock;
import com.conexa.agenda.model.Paciente;
import com.conexa.agenda.service.PacienteServiceImpl;
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
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PacienteControllerTest {

    @InjectMocks
    private PacienteController pacienteController;

    @Mock
    private PacienteServiceImpl pacienteServiceImpl;

    private Paciente paciente;

    @Before("")
    public void beforeEachTest() {
        paciente = Mockito.mock(Paciente.class);
    }


    @DisplayName("Teste de salvar paciente")
    @Test
    void test_save_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteServiceImpl.save(paciente)).thenReturn(paciente);
        assertEquals(HttpStatus.CREATED, pacienteController.save(paciente).getStatusCode());
    }

    @DisplayName("Teste de buscar todos os pacientes")
    @Test
    void test_getAll_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteServiceImpl.getAll()).thenReturn(Arrays.asList(paciente));
        assertEquals(HttpStatus.OK, pacienteController.gelAll().getStatusCode());
        assertEquals(1, pacienteController.gelAll().getBody().size());
    }


    @DisplayName("Teste de buscar um paciente por id")
    @Test
    void test_getPacienteById_success() {
        paciente = PacienteMock.setPaciente("Paulo", "5829482984", 25, "113134144");
        when(pacienteServiceImpl.getById("1")).thenReturn(java.util.Optional.of(paciente));
        assertEquals(HttpStatus.OK, pacienteController.findById("1").getStatusCode());
        assertEquals("Paulo", pacienteController.findById("1").getBody().getNome());
    }

    @DisplayName("Atualizar um paciente")
    @Test
    void test_update_success() {
        paciente = PacienteMock.setPaciente("Jose", "5829482984", 25, "113134144");
        when(pacienteServiceImpl.update(paciente)).thenReturn(paciente);
        assertEquals(HttpStatus.OK, pacienteController.update(paciente).getStatusCode());
        assertEquals("Jose", pacienteController.update(paciente).getBody().getNome());
    }

    @DisplayName("Deletar um paciente")
    @Test
    void test_delete_success() {
        paciente = PacienteMock.setPaciente("Jose", "5829482984", 25, "113134144");
        pacienteServiceImpl.save(paciente);
        pacienteServiceImpl.delete(String.valueOf(paciente.getId()));
        assertEquals(0, pacienteController.gelAll().getBody().size());
    }

}
