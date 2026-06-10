package com.jruedac.controlador;

import com.jruedac.dao.PersonaDAO;
import com.jruedac.modelo.Persona;
import java.sql.ResultSet;

public class PersonaControlador {

    private PersonaDAO personaDAO;

    public PersonaControlador() {
        personaDAO = new PersonaDAO();
    }

    public boolean guardarPersona(Persona persona) {
        return personaDAO.guardar(persona);
    }

    public boolean actualizarPersona(Persona persona) {
        return personaDAO.actualizar(persona);
    }

    public boolean eliminarPersona(int idPersona) {
        return personaDAO.eliminar(idPersona);
    }

    public ResultSet generarReporte() {
        return personaDAO.listarReporte();
    }
}
