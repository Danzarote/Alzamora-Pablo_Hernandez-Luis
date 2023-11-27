package com.backend.clinicaodontologica.dto.entrada.turno;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @NotNull(message = "El id del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del paciente")
    private Long idPaciente;

    @NotNull(message = "El id del odontologo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el id del odontologo")
    private Long idOdontologo;


    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora a reservar")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaYHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(Long idPaciente, Long idOdontologo, LocalDateTime fechaYHora) {
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fechaYHora = fechaYHora;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}