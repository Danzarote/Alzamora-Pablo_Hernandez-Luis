package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    List<Odontologo> odontologoLista = new ArrayList<>();


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        int id = odontologoLista.size() + 1;
        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
        odontologoLista.add(odontologoGuardado);
        LOGGER.info("Odontologo guardado: " + odontologoGuardado);
        return odontologo;
    }


    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Lista de Odontologos: " + odontologoLista);
        return odontologoLista;
    }

}