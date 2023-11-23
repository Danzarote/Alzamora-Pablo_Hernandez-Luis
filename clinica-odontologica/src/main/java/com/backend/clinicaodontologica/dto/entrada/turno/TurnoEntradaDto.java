package com.backend.clinicaodontologica.dto.entrada.turno;

<<<<<<< HEAD
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    private Long idPaciente;
    private Long idOdontologo;
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
=======
public class TurnoEntradaDto {
}
>>>>>>> 1491effbb700b4bb375cd110d3ab48890f54643a
