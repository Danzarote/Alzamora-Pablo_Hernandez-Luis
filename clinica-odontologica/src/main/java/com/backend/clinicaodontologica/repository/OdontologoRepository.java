package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    // Método para buscar odontólogos por matrícula
    Optional<Odontologo> findById(Long id);

    // Método para buscar odontólogos por apellido
    List<Odontologo> findByApellido(String apellido);

    // Método para buscar odontólogos por nombre y apellido
    List<Odontologo> findByNombreAndApellido(String nombre, String apellido);
}
