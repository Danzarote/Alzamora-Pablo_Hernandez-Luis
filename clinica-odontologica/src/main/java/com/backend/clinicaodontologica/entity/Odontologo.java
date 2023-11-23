package com.backend.clinicaodontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
    private int id;
    @Column(length =15)
    private String matricula;
    @Column(length =15)
    private String nombre;
    @Column(length =15)
=======
    private Long id;

    private String matricula;
    private String nombre;
>>>>>>> 1491effbb700b4bb375cd110d3ab48890f54643a
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

<<<<<<< HEAD
    public int getId() {
        return id;
    }

    public void setId(int id) {
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
>>>>>>> 1491effbb700b4bb375cd110d3ab48890f54643a
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }
}
=======

}
>>>>>>> 1491effbb700b4bb375cd110d3ab48890f54643a
