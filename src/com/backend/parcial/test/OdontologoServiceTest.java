package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontologoDaoH2;
import com.backend.parcial.dao.impl.OdontologoDaoMemoria;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/c1parcial;INIT=RUNSCRIPT FROM 'create.sql'", "tuki", "tuki");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void deberiaGuardarOdontologo() {
        Odontologo odontologo = new Odontologo(1, "Pablo", "Alzamora");

        Odontologo odontologoRegistrado = odontologoService.guardar(odontologo);
        Assertions.assertTrue(odontologoRegistrado.getId() != 0);

    }

    @Test
    public void deberiaRetornarListaNoVacia() {
        assertFalse(odontologoService.listar().isEmpty());

    }

    OdontologoService odontologoServiceMemoria = new OdontologoService(new OdontologoDaoMemoria());

    @Test
    public void deberiaGuardarEnMemoriaOdontologoYRetornarListaNoVacia() {
        Odontologo odontologo = new Odontologo(2, "Luis", "Hernandez");
        Odontologo odontologo2 = new Odontologo(3, "Lu", "Murga");
        odontologoServiceMemoria.guardar(odontologo);
        odontologoServiceMemoria.guardar(odontologo2);
        assertFalse(odontologoServiceMemoria.listar().isEmpty());

    }


}