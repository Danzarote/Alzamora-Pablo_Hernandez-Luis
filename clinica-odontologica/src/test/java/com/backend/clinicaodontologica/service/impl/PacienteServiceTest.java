package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.h2.jdbc.JdbcBatchUpdateException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PacienteServiceTest{

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarUnPacienteDeNombreJuanYRetornarElId() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 123456789, LocalDate.of(2023, 12, 24), new DomicilioEntradaDto("siempreviva", 1234, "siempreviva1", "provincia"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan", pacienteSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaEliminarElPacienteConId() {
        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));

    }
}
