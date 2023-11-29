package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;



    @Test
    void DeberiaDevolverUnaExcepcionPorPacienteConId1NoEncontrado() {
        TurnoEntradaDto turnoEntrada = new TurnoEntradaDto(1l, 1L, LocalDateTime.parse("2023-11-27T14:00:00") );
        try {
            turnoService.asignarTurno(turnoEntrada);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(BadRequestException.class, () -> turnoService.asignarTurno(turnoEntrada));
    }

    @Test
    void DeberiaDevolverUnaListaVaciaDeTurnos() {
        List<TurnoSalidaDto> turnoSalida = turnoService.listarTurnos();

        assertTrue(turnoSalida.isEmpty());
    }

    @Test
    void DeberiaDevolverUnaExcepcionAlIntentarEliminarUnTurnoConId1() {
        try {
            turnoService.eliminarTurno(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }
}