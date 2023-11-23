package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository,
                        OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void asignarTurno(TurnoEntradaDto turnoEntradaDto) {
        Paciente paciente = pacienteRepository.findById(turnoEntradaDto.getIdPaciente()).orElseThrow();
        Odontologo odontologo = odontologoRepository.findById(turnoEntradaDto.getIdOdontologo()).orElseThrow();

        // Verificar si la fecha y hora ya están ocupadas, implementar lógica según necesidad

        Turno nuevoTurno = new Turno();
        nuevoTurno.setFechaYHora(turnoEntradaDto.getFechaYHora());
        nuevoTurno.setPaciente(paciente);
        nuevoTurno.setOdontologo(odontologo);

        turnoRepository.save(nuevoTurno);
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        // Mapear la lista de entidades Turno a una lista de DTO TurnoSalidaDto
        return turnos.stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
    }

    // Puedes agregar más métodos según las necesidades específicas de tu aplicación
}
