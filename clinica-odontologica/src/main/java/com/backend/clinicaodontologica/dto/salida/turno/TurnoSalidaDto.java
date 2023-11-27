package com.backend.clinicaodontologica.dto.salida.turno;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;
    private Long idPaciente;
    private Long idOdontologo;
    private LocalDateTime fechaYHora;

    // constructores, getters y setters


    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, Long idPaciente, Long idOdontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TurnoSalidaDto{" +
                "id=" + id +
                ", idPaciente=" + idPaciente +
                ", idOdontologo=" + idOdontologo +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
