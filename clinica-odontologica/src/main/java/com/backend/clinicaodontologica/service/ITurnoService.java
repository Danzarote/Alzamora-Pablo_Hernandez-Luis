package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.turno.TurnoModificacionEntradaDto;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {
    void asignarTurno(TurnoEntradaDto turnoEntradaDto);

    List<TurnoSalidaDto> listarTurnos();

    // otras operaciones relacionadas con turnos, si las hay
}