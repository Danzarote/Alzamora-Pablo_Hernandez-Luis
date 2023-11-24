package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/asignar")
    public ResponseEntity<String> asignarTurno(@RequestBody TurnoEntradaDto turnoEntradaDto) {
        turnoService.asignarTurno(turnoEntradaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Turno asignado con éxito");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();
        return ResponseEntity.ok(turnos);
    }

    // otros métodos del controlador relacionados con turnos, si los hay
}
