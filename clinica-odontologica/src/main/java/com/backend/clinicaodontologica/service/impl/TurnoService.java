package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

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
    public void asignarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        Paciente paciente = pacienteRepository.findById(turnoEntradaDto.getIdPaciente()).orElseThrow(() -> new BadRequestException(
                "Paciente no encontrado " + turnoEntradaDto.getIdPaciente()));
        Odontologo odontologo = odontologoRepository.findById(turnoEntradaDto.getIdOdontologo()).orElseThrow(() -> new BadRequestException(
                "Odontologo no encontrado " + turnoEntradaDto.getIdOdontologo()));


            List<Turno> turnoAComparar = turnoRepository.findByFechaYHora(turnoEntradaDto.getFechaYHora());
        if(turnoAComparar.isEmpty()) {
            Turno nuevoTurno = new Turno();
            nuevoTurno.setFechaYHora(turnoEntradaDto.getFechaYHora());
            nuevoTurno.setPaciente(paciente);
            nuevoTurno.setOdontologo(odontologo);
            LOGGER.info("Turno Agendado: {}", JsonPrinter.toString(nuevoTurno));

            turnoRepository.save(nuevoTurno);
        }
        else {
            LOGGER.error("La fecha y hora ya estan ocupadas");
            throw new BadRequestException("La Fecha y Hora están ocupadas " + turnoEntradaDto.getFechaYHora());
            }
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        if(LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException{
        if (turnoRepository.findById(id).orElse(null) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }

    }

}
