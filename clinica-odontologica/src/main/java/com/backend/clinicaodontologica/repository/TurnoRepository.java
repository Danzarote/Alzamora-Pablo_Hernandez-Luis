package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // Puedes agregar métodos específicos de consulta si es necesario

    // Ejemplo de método de consulta para obtener todos los turnos para un paciente
    List<Turno> findByPaciente_Id(Long pacienteId);

    // Ejemplo de método de consulta para obtener todos los turnos para un odontólogo
    List<Turno> findByOdontologo_Id(Long odontologoId);

    // Ejemplo de método de consulta para obtener todos los turnos para una fecha y hora específicas
    List<Turno> findByFechaYHora(LocalDateTime fechaYHora);
}
