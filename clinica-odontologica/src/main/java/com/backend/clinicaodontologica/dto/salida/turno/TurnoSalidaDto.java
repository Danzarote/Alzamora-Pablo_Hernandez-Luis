package com.backend.clinicaodontologica.dto.salida.turno;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;
    private Long idPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private Long idOdontologo;
    private String nombreOdontologo;
    private String apellidoOdontologo;
    private LocalDateTime fechaYHora;

    // constructores, getters y setters


    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, Long idPaciente, String nombrePaciente, String apellidoPaciente, Long idOdontologo, String nombreOdontologo, String apellidoOdontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.idOdontologo = idOdontologo;
        this.nombreOdontologo = nombreOdontologo;
        this.apellidoOdontologo = apellidoOdontologo;
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

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getApellidoOdontologo() {
        return apellidoOdontologo;
    }

    public void setApellidoOdontologo(String apellidoOdontologo) {
        this.apellidoOdontologo = apellidoOdontologo;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreOdontologo() {
        return nombreOdontologo;
    }

    public void setNombreOdontologo(String nombreOdontologo) {
        this.nombreOdontologo = nombreOdontologo;
    }


    @Override
    public String toString() {
        return "TurnoSalidaDto{" +
                "id=" + id +
                ", idPaciente=" + idPaciente +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", apellidoPaciente='" + apellidoPaciente + '\'' +
                ", idOdontologo=" + idOdontologo +
                ", nombreOdontologo='" + nombreOdontologo + '\'' +
                ", apellidoOdontologo='" + apellidoOdontologo + '\'' +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
